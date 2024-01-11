import BaseClass from "../util/baseClass";
import axios from 'axios'

/**
 * Client to call the MusicPlaylistService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
 */
export default class PetClient  extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'getPet', 'createPet'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     * @param client The client that has been successfully loaded.
     */
    clientLoaded(client) {
        this.client = client;
        if (this.props.hasOwnProperty("onReady")){
            this.props.onReady();
        }
    }

    /**
     * Gets the concert for the given ID.
     * @param id Unique identifier for a concert
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The concert
     */
    async getPet(petId, errorCallback) {
        try {
            const response = await this.client.get(`Pet/petId/${petId}`);
            return response.data;
        } catch (error) {
            if (error.response && error.response.status === 404) {
                    errorCallback('Pet not found');
            } else {
                this.handleError("getPet", error, errorCallback)
            }
        }
    }


    async createPet(petData, errorCallback) {
        try {
        //     // Now, create the pet with the image URL included
        //     const body = {
        //         name: petData.name,
        //         petType: petData.petType,
        //         age: petData.age,
        //         imageUrl: petData.imageUrl
        //     };

            const response = await this.client.post(`Pet`, petData, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            return response.data;
        } catch (error) {
            this.handleError("createPet", error, errorCallback);
        }
    }
    async updatePet(petId, updatedPetData) {
        const response = await this.client.put(`Pet/petId/${petId}`, updatedPetData, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        return response.data;
    }

    /**
     * Helper method to log the error and run any error functions.
     * @param method
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(method, error, errorCallback) {
        console.error(method + " failed - " + error);
        if (error.response.data.message !== undefined) {
            console.error(error.response.data.message);
        }
        if (errorCallback) {
            errorCallback(method + " failed - " + error);
        }
    }
    async deletePet(petId) {
        try {
            const response = await this.client.delete(`Pet/petId/${petId}`);
            this.showMessage(`Deleted pet ${petId}`);

            // Based on what your server returns, you might return the response data or you might just return true
            return true;
        } catch (error) {
            this.handleError("deletePet", error);
            return null;
        }
    }
}
