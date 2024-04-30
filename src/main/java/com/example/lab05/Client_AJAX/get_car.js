
function getCar() {
    const id = document.getElementById('carId').value;
    fetch(`http://localhost:8080/cars/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(car => {
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


