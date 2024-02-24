function showLeaders(leaders, leaderListSection) {
    leaderListSection.innerHTML = "";

    leaders.forEach(leader => {
        let tableRow = document.createElement('tr');
        let usernameData = document.createElement('td');
        let fullNameData = document.createElement('td');
        let addressData = document.createElement('td');

        usernameData.innerHTML = leader.username;
        fullNameData.innerHTML = leader.fullName;
        addressData.innerHTML = leader.address;

        tableRow.appendChild(usernameData);
        tableRow.appendChild(fullNameData);
        tableRow.appendChild(addressData);

        leaderListSection.appendChild(tableRow);
    })
}

let companyLeaderUsername = sessionStorage.getItem('companyLeaderUsername');

/**
 * Handle show gather leader list
 */
document.getElementById("btn-show-list-gather-leader").addEventListener("click", function (event) {
    event.preventDefault();

    let leaderListSection = document.querySelector(".gather-leader-list .table tbody");

    let url = "http://localhost:8080/point-leader/gather/list";

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: companyLeaderUsername
    };

    fetch(url, options)
        .then(response => response.json())
        .then(leaders => {

            showLeaders(leaders, leaderListSection);
        })
        .catch(error => {
            console.error("Error:", error);
        });

});

/**
 * Handle show transaction leader list
 */
document.getElementById("btn-show-list-transaction-leader").addEventListener("click", function (event) {
    event.preventDefault();

    let leaderListSection = document.querySelector(".transaction-leader-list .table tbody");

    let url = "http://localhost:8080/point-leader/transaction/tools/list";

    fetch(url)
        .then(response => response.json())
        .then(leaders => {

            showLeaders(leaders, leaderListSection);
        })
        .catch(error => {
            console.error("Error:", error);
        });

});