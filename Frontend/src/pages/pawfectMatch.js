import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import PetClient from "../api/petClient";

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
        document.getElementById('create-form').addEventListener('submit', this.onCreate);
        this.client = new PetClient();

        this.dataStore.addChangeListener(this.renderPet)
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderPet() {
        let resultArea = document.getElementById("result-info");

        const pet = this.dataStore.get("pet");

        if (pet) {
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
        this.dataStore.set("pet", null);

        let result = await this.client.getPet(id, this.errorHandler);
        this.dataStore.set("Pet", result);
        if (result) {
            this.showMessage(`Got ${result.name}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("pet", null);

        let name = document.getElementById("create-name-field").value;

        const createdPet = await this.client.createPet(name, this.errorHandler);
        this.dataStore.set("pet", createdPet);

        if (createdPet) {
            this.showMessage(`Created ${createdPet.name}!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const pawfectMatch = new PawfectMatch();
    pawfectMatch.mount();
};

window.addEventListener('DOMContentLoaded', main);
