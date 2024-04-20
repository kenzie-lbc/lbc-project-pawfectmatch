// Furry_Guardian 'action menu' actions
document.addEventListener('DOMContentLoaded', function() {
    var actionSelect = document.getElementById('action-select');
    var addPetDiv = document.getElementById('add-pet');
    var searchPetDiv = document.getElementById('search-pet');
    var updatePetDiv = document.getElementById('update-pet');
    var deletePetDiv = document.getElementById('delete-pet');
    var chooseDiv = document.getElementById('choose'); // Get the 'choose' div

    function hideAllForms() {
        addPetDiv.classList.add('hidden');
        searchPetDiv.classList.add('hidden');
        updatePetDiv.classList.add('hidden');
        deletePetDiv.classList.add('hidden');
        chooseDiv.classList.add('hidden'); // Hide the 'choose' div by default
    }

    hideAllForms(); // Initially hide all forms
    chooseDiv.classList.remove('hidden'); // Show the 'choose' div by default

    actionSelect.addEventListener('change', function() {
        hideAllForms(); // Hide all forms first

        // Show the selected form
        var selectedAction = this.value;
        if(selectedAction === 'choose') {
            chooseDiv.classList.remove('hidden'); // Show the 'choose' div if selected
        } else {
            var formToShow = document.getElementById(selectedAction);
            if (formToShow) {
                formToShow.classList.remove('hidden');
            }
        }
    });
});
