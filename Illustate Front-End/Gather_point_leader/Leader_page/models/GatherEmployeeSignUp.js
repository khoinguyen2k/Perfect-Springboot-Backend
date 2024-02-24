class GatherEmployeeSignUp {
    constructor(formData) {
        this.username = formData.get('username');
        this.password = formData.get('password');
        this.fullName = formData.get('fullname');
        this.phoneNumber = formData.get('phone');
        this.gatherPointLeaderUsername = "gatherleader1";
    }
}