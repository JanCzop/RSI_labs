function removeTable() {
    const tbody = $('#carTableBody');
    if (tbody.length) {
        tbody.empty(); // Usuwamy zawartość tabeli
    }
}



function fetchAndGenerateTable() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/cars",
        dataType: "json",
        success: function(data) {
           // console.error(data);
            generateTableRows(data._embedded.carList);
        },
        error: function(xhr, status, error) {
            console.error('Error fetching data:', error);
        }
    });
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

        if (car._links.hasOwnProperty('repair'))
            row += '<td><button onclick="repairCar(' + car.id + ')">Repair</button></td>';
        else if (car._links.hasOwnProperty('damage'))
            row += '<td><button onclick="damageCar(' + car.id + ')">Damage</button></td>';

        row += '</tr>';
        tbody.append(row);
    });
}

function repairCar(carId) {
    console.log('Repairing car with ID:', carId);
}

function damageCar(carId) {
    console.log('Damaging car with ID:', carId);
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