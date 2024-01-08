import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import PetClient from "../api/PetClient";
import axios from "axios";

/**
 * Logic needed for the view playlist page of the website.
 */
class PawfectMatch extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGet', 'onCreate', 'renderPet'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('get-by-id-form').addEventListener('submit', this.onGet);
        document.getElementById('create-form').addEventListener('submit', this.handleFormSubmit);
        this.client = new PetClient();

        this.dataStore.addChangeListener(this.renderPet)
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderPet() {
        let resultArea = document.getElementById("result-info");

        const pet = this.dataStore.get("Pet");

        if (Pet) {
            resultArea.innerHTML = `
                <div>ID: ${pet.id}</div>
                <div>Name: ${pet.name}</div>
            `
        } else {
            resultArea.innerHTML = "No Item";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGet(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("id-field").value;
        this.dataStore.set("Pet", null);

        let result = await this.client.getPet(id, this.errorHandler);
        this.dataStore.set("Pet", result);
        if (result) {
            this.showMessage(`Got ${result.name}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }
    async uploadImageToCloudinary(imageFile) {
        const formData = new FormData();
        formData.append('file', imageFile);
        formData.append('upload_preset', 'ml_default');

        try {
            const response = await axios.post('https://api.cloudinary.com/v1_1/dehjkkblr/image/upload', formData);
            return response.data.url; // The image URL on Cloudinary
        } catch (error) {
            console.error('Error uploading image:', error);
            return null;
        }
    }

    // Function to handle form submission
    async getFormData() {
        // Get the image file from a file input field
        const imageFile = document.getElementById('pet-image-field').files[0];
        const imageUrl = await this.uploadImageToCloudinary(imageFile);

        // Get other pet details from form fields
        const name = document.getElementById('create-name-field').value;
        const petType = document.getElementById('pet-type-field').value;
        const age = parseInt(document.getElementById('pet-age-field').value, 10);
        return { name, petType, age, imageUrl };
    }

    async handleFormSubmit(event) {
        event.preventDefault();

        const petData = this.getFormData();
        const createdPet = await this.client.createPet(petData, this.errorHandler);
        if (createdPet) {
            this.showMessage(`Created ID: ${createdPet.petId} for ${createdPet.name}!`);
        } else {
            this.errorHandler("Error creating pet. Please, try again.");
        }
    }



    // async onCreate(event) {
    //     event.preventDefault();
    //
    //     const name = document.getElementById("create-name-field").value;
    //     const petType = document.getElementById("pet-type-field").value;
    //     const age = document.getElementById("pet-age-field").value;
    //     const imageFile = document.getElementById("pet-image-field").files[0];
    //
    //     const petData = {name, petType, age, imageFile};
    //     const createdPet = await this.client.createPet(petData, this.errorHandler);
    //
    //     if (createdPet) {
    //         this.showMessage(`Created ID: ${createdPet.petId} for ${createdPet.name}!`);
    //     } else {
    //         this.errorHandler("Error creating!  Try again...");
    //     }
    // }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const pawfectMatch = new PawfectMatch();
    pawfectMatch.mount();
};

window.addEventListener('DOMContentLoaded', main);
