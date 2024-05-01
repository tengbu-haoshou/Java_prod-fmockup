<template>
	<v-app>
		<common_header :hover="hover" @header_event="receive_header" />
		<div v-if="display == ''">
			<v-main>
			</v-main>
		</div>
		<div v-if="display == 'error'">
			<v-main>
				<v-container fluid>
					<v-row no-gutters>
						<v-col><div style="color: red;">{{ message }}</div></v-col>
					</v-row>
				</v-container>
			</v-main>
		</div>
		<div v-if="display == 'normal'">
			<v-main>
				<v-container fluid>
					<v-row no-gutters>
						<v-col cols="12">
							<v-tabs v-model="tab">
								<v-tab style="text-transform: none" href="/pages/home.html" value="Home"><h3 class="display-1">{{ text_tab_home }}</h3></v-tab>
								<v-tab style="text-transform: none" href="/pages/password.html" value="Password"><h3 class="display-1">{{ text_tab_password }}</h3></v-tab>
								<v-tab style="text-transform: none" href="/pages/about.html" value="About"><h3 class="display-1">{{ text_tab_about }}</h3></v-tab>
							</v-tabs>
						</v-col>
					</v-row>
					<v-row no-gutters><v-col>&nbsp;</v-col></v-row>
					<v-row no-gutters>
						<v-col><div style="color: red;">{{ message }}</div></v-col>
					</v-row>
					<v-row no-gutters>
						<v-col>fmockup (Mock-up for Business System Demonstration and Development Templates)</v-col>
					</v-row>
					<v-row no-gutters>
						<v-col>Copyright &copy; {{ years }} Xxxx Technologies Co., Ltd.</v-col>
					</v-row>
					<v-row no-gutters><v-col>&nbsp;</v-col></v-row>
					<v-row no-gutters>
						<v-col cols="auto"><v-btn variant="outlined" color="success" @click="ok()" style="text-transform: none">OK</v-btn></v-col>
					</v-row>
					<v-row no-gutters><v-col>&nbsp;</v-col></v-row>
					<v-row no-gutters>
						<v-col>{{ text_notice }}</v-col>
					</v-row>
				</v-container>
			</v-main>
		</div>
	</v-app>
	<common_texts @texts_event="receive_texts" />
	<common_valid @valid_event="receive_valid" />
</template>

<script setup>
	import axios from 'axios'
	import { ref } from 'vue'

	const years = ref('')
	const since = 2023
	const now = new Date()
	const year = now.getFullYear()
	if (year > since) {
		years.value = since + '-' + year
	} else {
		years.value = since
	}

	const hover = ref('About')
	const message = ref('')
	const display = ref('')
	const tab = ref('About')

	const text_tab_home = ref('')
	const text_tab_password = ref('')
	const text_tab_about = ref('')
	const text_notice = ref('')
	import common_texts from './commons/texts.vue'
	function receive_texts(texts_status, texts_message, texts_value) {
		if (texts_status == 'action-ok') {
			const texts_json = texts_value
			text_tab_home.value = texts_json.about_tab_home
			text_tab_password.value = texts_json.about_tab_password
			text_tab_about.value = texts_json.about_tab_about
			text_notice.value = texts_json.about_notice
		} else {
			display.value = 'error'
			message.value = texts_message
		}
	}

	import common_header from './commons/header.vue'
	function receive_header(header_status, header_message) {
		if (header_status != 'action-ok') {
			display.value = 'error'
			message.value = header_message
		}
	}

	import common_valid from './commons/valid.vue'
	function receive_valid(valid_status, valid_message) {
		if (valid_status == 'action-ok') {
			;
		} else {
			display.value = 'error'
			message.value = valid_message
		}
	}

	if (display.value == '') {
		display.value = 'normal'
	}

	function ok() {
		window.location.href = '/pages/home.html'
		return
	}
</script>
