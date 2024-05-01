<template>
</template>

<script setup>
	import axios from 'axios'

	const emits = defineEmits(['token_event'])
	axios.get('/session/token')
	.then (function(response) {
		emits('token_event', response.data.status, response.data.message, response.data.token)
	})
	.catch (function(error) {
		emits('token_event', '', 'Network trouble has occurred.', '')
	})
</script>
