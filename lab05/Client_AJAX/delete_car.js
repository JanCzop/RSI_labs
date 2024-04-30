function deleteCar() {
    const carId = $('#carId').val();

    $.ajax({
        url: `http://localhost:8080/cars/${carId}`,
        type: 'DELETE',
        success: function() {
            updateMessages(`Car with ID ${carId} deleted successfully`, false);
            if (!listed) {
                fetchAndGenerateTable();
            } else {
                toggleListButton();
            }
        },
        error: function(xhr, status, error) {
            if (xhr.status === 404) {
                updateMessages(`Car with ID ${carId} not found`, true);
            } else {
                console.error('Error deleting car:', error);
            }
        }
    });
}
