function repairCar() {
    const carId = $('#carId').val();

    $.ajax({
        url: `http://localhost:8080/cars/${carId}/repair`,
        type: 'PATCH',
        success: function() {
            updateMessages(`Car with ID ${carId} repaired successfully`, false);
            if (!listed) {
                fetchAndGenerateTable();
            } else {
                toggleListButton();
            }
        },
        error: function(xhr, status, error) {
            if (xhr.status === 409) {
                updateMessages(`Car with ID ${carId} cannot be repaired because it is already undamaged`, true);
            } else if (xhr.status === 404) {
                updateMessages(`Car with ID ${carId} not found`, true);
            } else {
                console.error('Error repairing car:', error);
            }
        }
    });
}
