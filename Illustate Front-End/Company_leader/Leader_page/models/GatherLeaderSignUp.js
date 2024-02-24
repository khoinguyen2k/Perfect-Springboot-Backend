class GatherLeaderSignUp {
    constructor(formData, companyLeaderUsername) {
        this.username = formData.get('username');
        this.password = formData.get('password');
        this.fullName = formData.get('fullname');
        this.address = formData.get('address');
        this.companyLeaderUsername = companyLeaderUsername;
    }
}