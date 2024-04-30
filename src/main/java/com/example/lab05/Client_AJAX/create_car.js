function createCar() {
    const carId = $('#carId').val();
    const carModel = $('#carModel').val();
    const carBrand = $('#carBrand').val();
    const carProductionYear = $('#carProductionYear').val();
    const carStatus = $('#carStatus').val();

    const carData = {
        id: carId,
        model: carModel,
        brand: carBrand,
        production_year: carProductionYear,
        status: carStatus
    };

    $.ajax({
        url: 'http://localhost:8080/cars',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(carData),
        success: function(data) {
            updateMessages(`Car with ID ${carId} added successfully`, false);
            generateTableRow(data);
        },
        error: function(xhr, status, error) {
            if (xhr.status === 400) {
                updateMessages(`Error adding car with ID ${carId}`, true);
            } else if (xhr.status === 409) {
                updateMessages(`Error adding car - car ${carId} already exists`, true);
            } else {
                console.error('Network response was not ok');
            }
        }
    });
}
