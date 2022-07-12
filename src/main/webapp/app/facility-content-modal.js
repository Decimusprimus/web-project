Vue.component("facility-content-modal", {
    data: function() {
    return {
        name: '',
        type: '',
        description: '',
        duration: '',
        price: '',
        filePreview: null,
        coaches: [],
        enableCoach: false,
        coach: null,
        selectedCoach: '',
        submitted: false,
        nameErrorMsg: '',
        validNameResponse: true,
        training: null,
    }
},
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
                    <label for="nameContent">Name</label>
                    <input type="text" class="form-control" id="nameContent" v-model="name" v-on:change="nameChange" v-bind:class="{'is-invalid' : !validName() && submitted}" required>
                    <div class="invalid-feedback">{{nameErrorMsg}}</div>
                </div>
                <div class="form-group">
                    <label for="typeContent">Type</label>
                    <select class="form-control" id="typeContent" v-model="type" v-on:change="selectChange" v-bind:class="{'is-invalid' : !type && submitted}" required>
                        <option>GYM AND CARDIO</option>
                        <option>GROUP TRAINING</option>
                        <option>PERSONAL TRAINING</option>
                        <option>SAUNA</option>
                        <option>MASSAGE</option>
                    </select>
                    <div class="invalid-feedback">Type is requeued</div>
                </div>
                <div class="form-group">
                    <label for="coachContent">Coach</label>
                    <select class="form-control" id="coachContent" v-model="selectedCoach" v-bind:disabled="!enableCoach" v-bind:class="{'is-invalid' : !validCoach() && submitted}">
                        <option v-for="item in coaches" v-on:click="selectCoach(item)">{{item.firstName}} {{item.lastName}}</option>
                    </select>
                    <div class="invalid-feedback">Coach is requeued</div>
                </div>
                <label for="imageContent">Image</label>
                    <div>
                        <div class="previewBlock" v-bind:style="logoImage">  
                        </div>
                        <input type="file" ref="imageContent"  accept="image/jpg" class="form-control" id="imageContent" v-on:input="selectImgFile" v-bind:class="{'is-invalid' : !filePreview && submitted}">
                        <div class="invalid-feedback">Image is requeued</div>
                    </div>
                <div class="form-group">
                    <label for="descriptionContent">Description</label>
                    <textarea  type="text" class="form-control" id="descriptionContent" v-model="description" ></textarea>
                </div>
                <div class="form-group">
                    <label for="durationContent">Duration</label>
                    <small class="text-muted">in minutes</small>
                    <input type="number" class="form-control" id="durationContent" v-model="duration" min="1">
                </div>
                <div class="form-group">
                    <label for="priceContent">Price</label>
                    <input type="number" class="form-control" id="priceContent" v-model="price" min="0">
                </div>
            </form>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" v-on:click="creteContent">Save changes</button>
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
        if(this.validName() && this.type && this.filePreview && this.validCoach()){
            return true;
        }
        return false;
    },
    creteContent: function(e) {
        e.preventDefault();
        this.submitted = true;
        const id = this.$route.params.id;
        if(this.validateForm()) {
            var t = this.type.replaceAll(' ', '_');
            if(!this.coach) {
                
                var dto = {
                    name: this.name,
                    trainingType: t,
                    duration: this.duration,
                    description: this.description,
                    price: this.price,
                    facilityId: id
                }
            } else {
                var dto = {
                    name: this.name,
                    trainingType: t,
                    duration: this.duration,
                    description: this.description,
                    price: this.price,
                    facilityId: id,
                    coachId: this.coach.id
                }
            }
            axios.post('/training/facility', JSON.stringify(dto))
            .then(res => {
                console.log(res.data);
                this.training = res.data;
                let data = new FormData();
                data.append('file', this.$refs.imageContent.files[0])
                let config = {
                    header : {
                        "Content-Type" : "multipart/form-data"
                    }
                }

                axios.post('/training/'+res.data.id+'/image', data, config)
                .then(res => {
                    console.log("uploaded");
                    this.success = true;
                    this.$emit('newTraining', this.training);
                    this.$refs.Close.click();
                })
                
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
    axios.get('/users/coaches')
    .then(res => {
        this.coaches = res.data;
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
                backgroundImage: '#c3c6c432'
            }
        }
    }
}
});