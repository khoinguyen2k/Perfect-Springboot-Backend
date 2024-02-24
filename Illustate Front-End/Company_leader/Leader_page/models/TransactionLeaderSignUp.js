class TransactionLeaderSignUp {
    constructor(formData) {
        this.username = formData.get('username');
        this.password = formData.get('password');
        this.fullName = formData.get('fullname');
        this.address = formData.get('address');
        this.gatherPointLeaderUsername = "gatherleader2";
    }
}