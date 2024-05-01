<template>
</template>

<script setup>
	import axios from 'axios'
	const lang = window.navigator.language
	/*
	 * Warning: file name is case sensitive.
	 */
	let file = ''
	if (lang == 'ja' || lang == 'zh-CN') {
		file = lang
	} else if (lang == 'en' || lang == 'en-US' || lang == 'en-GB' || lang == 'zh-TW') {
		file = 'en'
	} else {
		file = 'en'
	}

	const emits = defineEmits(['texts_event'])
	axios.get(`/texts/text_${file}.json`)
	.then (function(response) {
		emits('texts_event', 'action-ok', '', response.data)
	})
	.catch (function(error) {
		axios.get('/texts/text_en.json')
		.then (function(response) {
			emits('texts_event', 'action-ok', '', response.data)
		})
		.catch (function(error) {
			emits('texts_event', '', 'File trouble has occurred.', null)
		})
	})
</script>
