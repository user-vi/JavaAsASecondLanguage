<template>
    <div id="view-flits">
        <h2 class="bv-no-focus-ring">All flits</h2>
        <div v-for="flit in flits" v-bind:key="flit">
            <b-card
                    v-bind:title="flit.userName"
                    tag="article"
                    style="max-width: 20rem;"
                    class="mb-2"
            >
                <b-card-text>
                    {{flit.content}}
                </b-card-text>
            </b-card>
        </div>
    </div>
</template>

<script>

    import axios from 'axios';

    export default {
        name: 'ViewFlits',
        data() {
            return {
                flits: [],
                errorMessage: null
            }
        },
        methods: {
            load() {
                axios.get('/flit/discover')
                    .then(response => {
                        this.$data.flits = response.data.data;
                        this.$data.errorMessage = response.data.errorMessage;
                    })
                    .catch(error => {
                        console.log('ERROR: ' + error.response.errorMessage);
                    })
            }
        },
        mounted() {
            this.load();
        }
    }
</script>

<style>
</style>