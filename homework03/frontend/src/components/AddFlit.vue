<template>
    <div>
        <b-card class="card-login">
            <b-card-title>Add new flit</b-card-title>
            <b-form @submit="onSubmit" @reset="onReset">
                <b-form-group
                        id="input-group-1"
                        label="Token: "
                        label-for="input-1"
                        description="Token is generated after registration"
                >
                    <b-form-input id="input-1" v-model="form.userToken" placeholder="Enter your token"></b-form-input>
                </b-form-group>

                <b-form-group
                        id="input-group-2"
                        label="Flit text:"
                        label-for="input-2"
                >
                    <b-form-input id="input-2" v-model="form.content" placeholder="Enter flit text"></b-form-input>
                </b-form-group>

                <b-button type="submit" variant="primary">Submit</b-button>
                <b-button type="reset" variant="danger">Reset</b-button>
            </b-form>
        </b-card>
        <b-card class="mt-3" header="Form Data Result">
            <pre class="m-0">{{ form }}</pre>
        </b-card>
    </div>
</template>

<script>

    import axios from 'axios';

    export default {
        name: 'AddFlit',
        data() {
            return {
                form: {
                    userToken: "",
                    content: ""
                }
            }
        },
        methods: {
            onSubmit: function (event) {
                event.preventDefault()
                axios.post('/flit/add', {
                    "userToken": this.form.userToken,
                    "content": this.form.content
                }).then(response => {
                    alert(JSON.stringify(response.data))
                }).catch(error => {
                    console.log('ERROR: ' + error);
                })
                this.form.userToken = ''
                this.form.content = ''
            },
            onReset(evt) {
                evt.preventDefault()
                this.form.userToken = ''
                this.form.content = ''
            }
        }
    }

</script>

<style>
</style>