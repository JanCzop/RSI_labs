function updateCar() {
    const carId = $('#carId').val();
    const carModel = $('#carModel').val();
    const carBrand = $('#carBrand').val();
    const carProductionYear = $('#carProductionYear').val();
    const carStatus = $('#carStatus').val();

    $.ajax({
        url: `http://localhost:8080/cars/${carId}`,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({
            id: carId,
            model: carModel,
            brand: carBrand,
            production_year: carProductionYear,
            status: carStatus
        }),
        success: function() {
            updateMessages(`Car with ID ${carId} updated successfully`, false);
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
                console.error('Error updating car:', error);
                updateMessages(`Error updating car with ID ${carId}`, true);
            }
        }
    });
}
