document.getElementById("btn-show-list-employee").addEventListener("click", function (event) {
    event.preventDefault();

    let employeeListBody = document.querySelector(".employee-list .table tbody");

    let url = "http://localhost:8080/employee/gather/list";
    let gatherLeaderUsername = sessionStorage.getItem("gatherLeaderUsername");

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: gatherLeaderUsername
    };

    fetch(url, options)
        .then(response => response.json())
        .then(employees => {

            employees.forEach(employee => {
                let tableRow = document.createElement('tr');
                let usernameData = document.createElement('td');
                let fullNameData = document.createElement('td');
                let phoneNumberData = document.createElement('td');

                usernameData.innerHTML = employee.username;
                fullNameData.innerHTML = employee.fullName;
                phoneNumberData.innerHTML = employee.phoneNumber;

                tableRow.appendChild(usernameData);
                tableRow.appendChild(fullNameData);
                tableRow.appendChild(phoneNumberData);

                employeeListBody.appendChild(tableRow);
            });
        })
        .catch(error => {
            console.error("Error:", error);
        });

});