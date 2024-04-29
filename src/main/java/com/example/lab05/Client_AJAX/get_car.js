// get_car.js

// Funkcja do pobierania danych o samochodzie o podanym ID
function getCar(id) {
    fetch(`http://localhost:8080/cars/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(car => {
            // Wywołanie funkcji do wygenerowania wiersza tabeli z danymi o samochodzie
            generateTableRow(car);
        })
        .catch(error => {
            if (error.message === 'Network response was not ok') {
                updateMessages(`Komunikat: Car ${id} not found`,true);
            } else {
                console.error('Error fetching car data:', error);
            }
        });
}

// Funkcja do generowania wiersza tabeli z danymi o samochodzie
function generateTableRow(car) {
    var tbody = document.querySelector('#carTableBody');
    if (!tbody) {
        console.error('Table body element not found.');
        return;
    }

    var row = '<tr>';
    row += '<td>' + car.id + '</td>';
    row += '<td>' + car.model + '</td>';
    row += '<td>' + car.brand + '</td>';
    row += '<td>' + car.production_year + '</td>';
    row += '<td>' + car.status + '</td>';
    row += '</tr>';

    tbody.innerHTML = row;
}
