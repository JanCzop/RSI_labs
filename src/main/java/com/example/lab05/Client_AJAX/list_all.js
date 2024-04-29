function removeTable() {
    const table = document.getElementById('carTableBody');
    if (table) {
        table.innerHTML = ''; // Usuwamy zawartość tabeli
    }
}

function generateTableRows(cars) {
    const tbody = document.querySelector('#carTableBody');
    if (!tbody) {
        console.error('Table body element not found.');
        return;
    }
    tbody.innerHTML = '';

    cars.forEach(function(car) {
        let row = '<tr>';
        row += '<td>' + car.id + '</td>';
        row += '<td>' + car.model + '</td>';
        row += '<td>' + car.brand + '</td>';
        row += '<td>' + car.production_year + '</td>';
        row += '<td>' + car.status + '</td>';
        row += '</tr>';
        tbody.innerHTML += row;
    });
}

function fetchAndGenerateTable() {
    fetch('http://localhost:8080/cars')
        .then(response => response.json())
        .then(data => generateTableRows(data))
        .catch(error => console.error('Error fetching data:', error));
}

function toggleListButton() {
    const listButton = document.getElementById('listButton');
    if (listed) {
        listButton.textContent = 'Hide Cars';
        fetchAndGenerateTable();
    } else {
        listButton.textContent = 'List All Cars';
        removeTable();
    }
    listed = !listed;
}
