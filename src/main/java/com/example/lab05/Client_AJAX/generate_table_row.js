function generateTableRow(car) {
    listed = false;
    toggleListButton();

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