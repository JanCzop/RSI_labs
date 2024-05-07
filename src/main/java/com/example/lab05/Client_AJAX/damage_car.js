function damageCar(carId) {

    $.ajax({
        url: `http://localhost:8080/cars/${carId}/damage`,
        type: 'PATCH',
        success: function() {
            updateMessages(`Car with ID ${carId} damaged successfully`, false);
            if (!listed) {
                fetchAndGenerateTable();
            } else {
                toggleListButton();
            }
        },
        error: function(xhr, status, error) {
            if (xhr.status === 409) {
                updateMessages(`Car with ID ${carId} cannot be damaged because it is already damaged`, true);
            } else if (xhr.status === 404) {
                updateMessages(`Car with ID ${carId} not found`, true);
            } else {
                console.error('Error repairing car:', error);
            }
        }
    });
}
