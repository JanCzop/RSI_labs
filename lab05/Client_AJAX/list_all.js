function removeTable() {
    const tbody = $('#carTableBody');
    if (tbody.length) {
        tbody.empty(); // Usuwamy zawartość tabeli
    }
}

function generateTableRows(cars) {
    const tbody = $('#carTableBody');
    if (!tbody.length) {
        console.error('Table body element not found.');
        return;
    }
    tbody.empty();

    cars.forEach(function(car) {
        let row = '<tr>';
        row += '<td>' + car.id + '</td>';
        row += '<td>' + car.model + '</td>';
        row += '<td>' + car.brand + '</td>';
        row += '<td>' + car.production_year + '</td>';
        row += '<td>' + car.status + '</td>';
        row += '</tr>';
        tbody.append(row);
    });
}

function fetchAndGenerateTable() {
    $.ajax({
        url: 'http://localhost:8080/cars',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            generateTableRows(data);
        },
        error: function(xhr, status, error) {
            console.error('Error fetching data:', error);
        }
    });
}

function toggleListButton() {
    const listButton = $('#listButton');
    if (listed) {
        listButton.text('Hide Cars');
        fetchAndGenerateTable();
    } else {
        listButton.text('List All Cars');
        removeTable();
    }
    listed = !listed;
}
