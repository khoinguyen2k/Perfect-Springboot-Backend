function showGatherToGatherDeliverList() {

    let deliverSection = document.getElementById('gather-to-gather-deliver-list');

    let url = BASE_URL + "deliver/gather-to-gather/list";

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: gatherEmployeeUsername
    };

    fetch(url, options)
        .then(response => response.json())
        .then(carts => {

            carts.forEach(cart => {
                const option = document.createElement('option');
                option.value = cart.id;
                option.text = cart.id + ' - ' + cart.sendGatherPoint.username + ' - ' + cart.sendTime;

                deliverSection.appendChild(option);
            })

        })
        .catch(error => {
            console.error("Error:", error);
        });

}

function showTransactionToGatherDeliverList() {
    let deliverSection = document.getElementById('transaction-to-gather-deliver-list');

    let url = BASE_URL + "deliver/transaction-to-gather/list";

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: gatherEmployeeUsername
    };

    fetch(url, options)
        .then(response => response.json())
        .then(carts => {

            carts.forEach(cart => {
                const option = document.createElement('option');
                option.value = cart.id;
                option.text = cart.id + ' - ' + cart.transactionPointLeader.username + ' - ' + cart.sendTime;

                deliverSection.appendChild(option);
            })

        })
        .catch(error => {
            console.error("Error:", error);
        });
}


showGatherToGatherDeliverList();
showTransactionToGatherDeliverList()