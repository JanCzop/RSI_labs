function deleteCar() {
    const carId = document.getElementById('carId').value;

    fetch(`http://localhost:8080/cars/${carId}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                updateMessages(`Car with ID ${carId} deleted successfully`, false);
                if(!listed) {fetchAndGenerateTable();}
                else toggleListButton();
            } else if (response.status === 404) {
                updateMessages(`Car with ID ${carId} not found`, true);
            } else {
                throw new Error('Network response was not ok');
            }
        })
        .catch(error => {
            if (error.message === 'Network response was not ok') {
                updateMessages(`Error deleting car with ID ${carId}`, true);
            } else {
                console.error('Error deleting car:', error);
            }
        });
}
