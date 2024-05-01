<template>
	<v-app>
		<common_header_for_login :logoutButton="logoutButton" @header_event="receive_header" />
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
						<v-col><h3 class="display-1">{{ text_body_title }}</h3></v-col>
					</v-row>
					<v-row no-gutters>
						<v-col><div style="color: red;">{{ message }}</div></v-col>
					</v-row>
					<v-row no-gutters>
						<v-col>
							<v-form>
								<v-container fluid>
									<v-row no-gutters>
										<v-col :cols="field_md">
											<div style="font-size: small; color: red;">{{ messages.userName }}</div>
											<v-text-field autocomplete="off" prepend-icon="mdi-account" clearable :label="text_form_userId" v-model="userName" />
										</v-col>
									</v-row>
									<v-row no-gutters>
										<v-col :cols="field_md">
											<div style="font-size: small; color: red;">{{ messages.password }}</div>
											<v-text-field
												autocomplete="off" prepend-icon="mdi-lock" clearable :label="text_form_password" v-model="password"
												v-bind:type="showPassword ? 'text' : 'password'"
												v-bind:append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
												@click:append="showPassword = ! showPassword" />
										</v-col>
									</v-row>
									<v-row no-gutters>
										<v-col cols="auto">
											<v-btn variant="outlined" color="success" @click="login()" :loading="loading" style="text-transform: none">{{ text_form_login }}</v-btn>
										</v-col>
									</v-row>
								</v-container>
							</v-form>
						</v-col>
					</v-row>
				</v-container>
			</v-main>
		</div>
	</v-app>
	<common_texts @texts_event="receive_texts" />
</template>

<script setup>
	import axios from 'axios'
	import { ref } from 'vue'

	const message = ref('')
	const messages = ref({
		'userName': '',
		'password': '',
	})

	const field_md = ref(0)
	const field_lg = ref(0)
	const width = window.innerWidth
	if (width < 600) {
		field_md.value = 12
		field_lg.value = 12
	} else if (width < 1024) {
		field_md.value = 6
		field_lg.value = 10
	} else {
		field_md.value = 4
		field_lg.value = 8
	}

	const loading = ref(true)
	const display = ref('')
	const logoutButton = ref(false)

	const text_body_title = ref('')
	const text_form_userId = ref('')
	const text_form_password = ref('')
	const text_form_login = ref('')
	import common_texts from './commons/texts.vue'
	function receive_texts(texts_status, texts_message, texts_value) {
		if (texts_status == 'action-ok') {
			const texts_json = texts_value
			text_body_title.value = texts_json.login_body_title
			text_form_userId.value = texts_json.login_form_userId
			text_form_password.value = texts_json.login_form_password
			text_form_login.value = texts_json.login_form_login
		} else {
			display.value = 'error'
			message.value = texts_message
		}
	}

	import common_header_for_login from './commons/header_for_login.vue'
	function receive_header(header_status, header_message) {
		if (header_status != 'action-ok') {
			display.value = 'error'
			message.value = header_message
		}
	}

	const showPassword = ref(false)
	const userName = ref('')
	const password = ref('')

	const formData = new FormData()
	axios.post('/session/unauth', formData)
	.then (function(response) {
		;
	})
	.catch (function(error) {
		message.value = 'Network trouble has occurred.'
	})

	async function login() {
		loading.value = true
		const formData = new FormData()
		formData.append('userName', userName.value != null ? userName.value : '')
		formData.append('password', password.value != null ? password.value : '')
		await axios.post('/session/auth', formData)
		.then (function(response) {
			if (response.data.status == 'action-ok-expired') {
				window.location.href = '/pages/login_expired.html'
				return
			} else if (response.data.status == 'action-ok') {
				window.location.href = '/pages/home.html'
				return
			} else {
				message.value = response.data.message
				messages.value = response.data.messageMap
			}
		})
		.catch (function(error) {
			message.value = 'Network trouble has occurred.'
		})
		loading.value = false
		return
	}

	loading.value = false
	if (display.value == '') {
		display.value = 'normal'
	}
</script>
