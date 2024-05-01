<template>
</template>

<script setup>
	import axios from 'axios'

	const emits = defineEmits(['valid_event'])
	axios.get('/session/valid')
	.then (function(response) {
		emits('valid_event', response.data.status, response.data.message)
	})
	.catch (function(error) {
		emits('valid_event', '', 'Network trouble has occurred.')
	})
</script>
