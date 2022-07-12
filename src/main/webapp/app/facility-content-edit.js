Vue.component("facility-content-edit", {
    data: function() {
    return {
        name: '',
        type: '',
        description: '',
        duration: '',
        filePreview: null,
        coaches: [],
        enableCoach: false,
        coach: null,
        selectedCoach: '',
        submitted: false,
        nameErrorMsg: '',
        validNameResponse: true,
    }
},
props: ['content'],
template: `
<div class="modal fade" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true" >
<div class="modal-dialog ">
    <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="modalLabel">Create new content</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" ref="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="modal-body">
            <form>
                <div class="form-group">
                    <label for="nameContentEdit">Name</label>
                    <input type="text" class="form-control" id="nameContentEdit" v-model="name" v-on:change="nameChange" v-bind:class="{'is-invalid' : !validName() && submitted}" required>
                    <div class="invalid-feedback">{{nameErrorMsg}}</div>
                </div>
                <div class="form-group">
                    <label for="typeContentEdit">Type</label>
                    <select class="form-control" id="typeContentEdit" v-model="type" v-on:change="selectChange" v-bind:class="{'is-invalid' : !type && submitted}" required>
                        <option>GYM AND CARDIO</option>
                        <option>GROUP TRAINING</option>
                        <option>PERSONAL TRAINING</option>
                        <option>SAUNA</option>
                        <option>MASSAGE</option>
                    </select>
                    <div class="invalid-feedback">Type is requeued</div>
                </div>
                <div class="form-group">
                    <label for="coachContentEdit">Coach</label>
                    <select class="form-control" id="coachContentEdit" v-model="selectedCoach" v-bind:disabled="!enableCoach" v-bind:class="{'is-invalid' : !validCoach() && submitted}">
                        <option v-for="item in coaches" v-on:click="selectCoach(item)">{{item.firstName}} {{item.lastName}}</option>
                    </select>
                    <div class="invalid-feedback">Coach is requeued</div>
                </div>
                <div class="form-group">
                    <label for="descriptionContentEdit">Description</label>
                    <textarea  type="text" class="form-control" id="descriptionContentEdit" v-model="description" ></textarea>
                </div>
                <div class="form-group">
                    <label for="durationContentEdit">Duration</label>
                    <small class="text-muted">in minutes</small>
                    <input type="number" class="form-control" id="durationContentEdit" v-model="duration" min="1">
                </div>
            </form>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" v-on:click="updateContent">Save changes</button>
        </div>

    </div>
</div>
</div>
`,
methods: {
    selectImgFile: function() {
        let fileInput = this.$refs.imageContent
        let imgFile = fileInput.files

        if (imgFile && imgFile[0]) {
            let reader = new FileReader();
            reader.onload = e => {
                this.filePreview = e.target.result
            }
            reader.readAsDataURL(imgFile[0])
            this.$emit('fileInput', imgFile[0])
        }
    },
    selectChange: function(e) {
        var value = e.target.value;
        if(value === 'GROUP TRAINING' || value === 'PERSONAL TRAINING'){
            this.enableCoach = true;
        } else {
            this.enableCoach = false;
            this.coach = null;
        }
    },
    selectCoach: function(item) {
        this.coach = item;
    },
    nameChange: function() {
        if(!this.validNameResponse) {
            this.validNameResponse = true;
        }
    },
    validName: function() {
        if(!this.name) {
            this.nameErrorMsg = 'Name is requeued'
            return false;
        } else if(!this.validNameResponse) {
            this.nameErrorMsg = 'Content with that name already exists'
            return false;
        } 
        this.nameErrorMsg = '';
        return true;
    },
    validCoach: function() {
        if(this.enableCoach && !this.selectedCoach) {
            return false;
        } 
        return true;
    },
    validateForm: function() {
        if(this.validName() && this.type && this.validCoach()){
            return true;
        }
        return false;
    },
    updateContent: function(e) {
        e.preventDefault();
        this.submitted = true;
        if(this.validateForm()) {
            var t = this.type.replaceAll(' ', '_');
            if(!this.coach) {
                var dto = {
                    name: this.name,
                    trainingType: t,
                    duration: this.duration,
                    description: this.description,
                    facilityId: this.content.id
                }
            } else {
                var dto = {
                    name: this.name,
                    trainingType: t,
                    duration: this.duration,
                    description: this.description,
                    facilityId: this.content.id,
                    coachId: this.coach.id
                }
            }
            console.log(dto);
            axios.put('/training/'+this.content.id, JSON.stringify(dto))
            .then(res => {
                this.$refs.Close.click();
                this.$router.go();
            })
            .catch(err => {
                if(err.response.data === 'Name taken') {
                    this.validNameResponse = false;
                }
            })
        }
    }
},
mounted() {
    this.name = this.content.name
    this.type = this.content.trainingType.replaceAll('_', ' ')
    this.description = this.content.description
    this.duration = this.content.duration
    axios.get('/users/coaches')
    .then(res => {
        this.coaches = res.data;
        this.coaches.forEach(element => {
            if(element.id === this.content.coachId){
                this.coach = element;
                this.selectCoach = element.firstName + ' ' + element.lastName;
            }
        });
    })
},
computed: {
    logoImage () {
        if(this.filePreview){
            return {
                backgroundImage: `url(${this.filePreview})`
            }
        } else {
            return {
                backgroundImage: 'url("\'/training/" +this.content.id+"/image"\')'
            }
        }
    }
}
});