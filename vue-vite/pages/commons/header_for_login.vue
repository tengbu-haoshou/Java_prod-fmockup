<template>
	<v-app-bar color="blue" app dark>
		<v-app-bar-title><h3 class="display-1">{{ text_title }}</h3></v-app-bar-title>
		<v-btn v-if="logoutButton" variant="outlined" color="white" @click="logout()" style="text-transform: none">{{ text_logout }}</v-btn>
	</v-app-bar>
	<v-footer color="blue" app dark>Copyright &copy; {{ years }} {{ text_company }}</v-footer>
	<common_texts @texts_event="receive_texts" />
</template>

<script setup>
	import { ref } from 'vue'
	import axios from 'axios'
	const props = defineProps({
		logoutButton: Boolean,
	})
	const logoutButton = ref(props.logoutButton)

	const footer = ref(false)
	const width = window.innerWidth
	if (width < 1024) {
		footer.value = true
	}

	// if want to footer is desabled on mobile, delete as follows:
	footer.value = true

	const text_title = ref('')
	const text_company = ref('')
	const text_logout = ref('')
	const years = ref('')
	const since = 2023
	const now = new Date()
	const year = now.getFullYear()
	if (year > since) {
		years.value = since + '-' + year
	} else {
		years.value = since
	}

	const emits = defineEmits(['header_event'])
	import common_texts from './texts.vue'
	function receive_texts(texts_status, texts_message, texts_value) {
		if (texts_status == 'action-ok') {
			const texts_json = texts_value
			text_title.value = texts_json.common_header_title
			text_company.value = texts_json.common_footer_company
			text_logout.value = texts_json.common_header_form_logout
		} else {
			emits('header_event', texts_message)
		}
		return
	}

	function logout() {
		const formData = new FormData()
		axios.post('/session/unauth', formData)
		.then (function(response) {
			window.location.href = '/pages/login.html'
			return
		})
		.catch (function(error) {
			// Cannot return event in here, and header does not have message line, so only alert in here.
			alert('Network trouble has occurred.')
		})
		return
	}
</script>
