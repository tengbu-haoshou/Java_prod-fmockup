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
						<v-col>
							<v-form>
								<v-container fluid>
									<v-row no-gutters>
										<v-col :cols="field_md">
											<div style="font-size: small; color: red;">{{ messages.currentPassword }}</div>
											<v-text-field
												autocomplete="off" prepend-icon="mdi-lock" clearable :label="text_form_currentPassword" v-model="currentPassword" 
												v-bind:type="showCurrentPassword ? 'text' : 'password'"
												v-bind:append-icon="showCurrentPassword ? 'mdi-eye' : 'mdi-eye-off'"
												@click:append="showCurrentPassword = ! showCurrentPassword"
											/>
										</v-col>
									</v-row>
									<v-row no-gutters align="center">
										<v-col :cols="field_md">
											<div style="font-size: small; color: red;">{{ messages.newPassword }}</div>
											<v-text-field
												autocomplete="off" prepend-icon="mdi-lock" clearable :label="text_form_newPassword" v-model="newPassword"
												v-bind:type="showNewPassword ? 'text' : 'password'"
												v-bind:append-icon="showNewPassword ? 'mdi-eye' : 'mdi-eye-off'"
												@click:append="showNewPassword = ! showNewPassword"
											/>
										</v-col>
									</v-row>
									<v-row no-gutters>
										<v-col :cols="field_md">
											<div style="font-size: small; color: red;">{{ messages.confirmPassword }}</div>
											<v-text-field
												autocomplete="off" prepend-icon="mdi-lock" clearable :label="text_form_confirmPassword" v-model="confirmPassword"
												v-bind:type="showConfirmPassword ? 'text' : 'password'"
												v-bind:append-icon="showConfirmPassword ? 'mdi-eye' : 'mdi-eye-off'"
												@click:append="showConfirmPassword = ! showConfirmPassword"
											/>
										</v-col>
									</v-row>
									<v-row no-gutters>							
										<v-col><div style="color: red;">{{ text_notice_change }}</div></v-col>
									</v-row>
										<v-row no-gutters><v-col>&nbsp;</v-col></v-row>									<v-row no-gutters>
										<v-col cols="auto">
											<v-btn variant="outlined" color="success" @click="save()" :loading="loading" style="text-transform: none">{{ text_form_save }}</v-btn>
											&nbsp;<v-btn variant="outlined" color="primary" @click="cancel()" style="text-transform: none">{{ text_form_cancel }}</v-btn>
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
	<common_token @token_event="receive_token" />
</template>

<script setup>
	import axios from 'axios'
	import { ref } from 'vue'

	const hover = ref('Password')
	const message = ref('')
	const messages = ref({
		'currentPassword': '',
		'newPassword': '',
		'confirmPassword': '',
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
	const tab = ref('Password')

	const text_tab_home = ref('')
	const text_tab_password = ref('')
	const text_tab_about = ref('')
	const text_form_currentPassword = ref('')
	const text_form_newPassword = ref('')
	const text_form_confirmPassword = ref('')
	const text_form_save = ref('')
	const text_form_cancel = ref('')
	const text_notice_change = ref('')
	import common_texts from './commons/texts.vue'
	function receive_texts(texts_status, texts_message, texts_value) {
		if (texts_status == 'action-ok') {
			const texts_json = texts_value
			text_tab_home.value = texts_json.password_tab_home
			text_tab_password.value = texts_json.password_tab_password
			text_tab_about.value = texts_json.password_tab_about
			text_form_currentPassword.value = texts_json.password_form_currentPassword
			text_form_newPassword.value = texts_json.password_form_newPassword
			text_form_confirmPassword.value = texts_json.password_form_confirmPassword
			text_form_save.value = texts_json.password_form_save
			text_form_cancel.value = texts_json.password_form_cancel
			text_notice_change.value = texts_json.password_notice_change
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

	const token = ref('')
	import common_token from './commons/token.vue'
	function receive_token(token_status, token_message, token_value) {
		if (token_status == 'action-ok') {
			token.value = token_value
		} else {
			display.value = 'error'
			message.value = token_message
		}
		return
	}

	const showCurrentPassword = ref(false)
	const showNewPassword = ref(false)
	const showConfirmPassword = ref(false)
	const currentPassword = ref('')
	const newPassword = ref('')
	const confirmPassword = ref('')

	loading.value = false
	if (display.value == '') {
		display.value = 'normal'
	}

	async function save() {
		loading.value = true
		const formData = new FormData()
		formData.append('token', token.value)
		formData.append('currentPassword', currentPassword.value != null ? currentPassword.value : '')
		formData.append('newPassword', newPassword.value != null ? newPassword.value : '')
		formData.append('confirmPassword', confirmPassword.value != null ? confirmPassword.value : '')
		await axios.post('/session/enabled', formData)
		.then (function(response) {
			if (response.data.status == 'action-ok') {
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

	function cancel() {
		window.location.href = '/pages/home.html'
		return
	}
</script>
