class GatherLeaderInfo {
    constructor(jsonText) {
        let obj = JSON.parse(jsonText);
        this.fullName = obj.fullName;
        this.address = obj.address;
    }

    showInfo() {
        let fullNameBox = document.getElementById("leader-info__fullname");
        let addressBox = document.getElementById("leader-info__address");

        fullNameBox.innerHTML = this.fullName;
        addressBox.innerHTML = this.address;
    }
}