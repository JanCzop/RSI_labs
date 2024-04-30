function getCar() {
    const id = $("#carId").val();
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/cars/${id}`,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(response) {
            generateTableRow(response);
        },
        error: function(xhr, status, error) {
            if (xhr.status === 404) {
                updateMessages(`Car ${id} not found`, true);
            } else {
                console.error('Error fetching car data:', error);
            }
        }
    });
}
