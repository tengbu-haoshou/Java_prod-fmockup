<template>
	<v-icon v-if="mode == 'modify'" large @click="popup('modify')">mdi-pencil</v-icon>
	<v-icon v-if="mode == 'drop'" large @click="popup('drop')">mdi-delete</v-icon>
	<v-dialog v-model="dialog" activator="#menu-popup" scrollable persistent no-click-animation>
		<template v-slot:activator="{on, attrs}">
			<v-icon v-if="mode == 'add'" large @click="popup('add')">mdi-plus-circle</v-icon>
		</template>
		<v-card>
			<div v-if="display == ''">
			</div>
			<div v-if="display == 'error'">
				<v-container fluid>
					<v-row no-gutters>
						<v-col><div style="color: red;">{{ message }}</div></v-col>
					</v-row>
				</v-container>
			</div>
			<div v-if="display == 'normal'">
				<v-container fluid>
					<v-row no-gutters>
						<v-col v-if="mode == 'add'"><h3 class="display-1">{{ text_title_add }}</h3></v-col>
						<v-col v-if="mode == 'modify'"><h3 class="display-1">{{ text_title_modify }}</h3></v-col>
						<v-col v-if="mode == 'drop'"><h3 class="display-1">{{ text_title_drop }}</h3></v-col>
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
											<div style="font-size: small; color: red;">{{ messages.xxxxName }}</div>
											<v-text-field v-if="disabled == true" disabled autocomplete="off" prepend-icon="mdi-account-multiple" :label="text_form_name" v-model="xxxxName" />
											<v-text-field v-if="disabled == false" autocomplete="off" prepend-icon="mdi-account-multiple" clearable :label="text_form_name" v-model="xxxxName" />
										</v-col>
									</v-row>
									<v-row no-gutters>
										<v-col :cols="field_lg">
											<div style="font-size: small; color: red;">{{ messages.xxxxFlag }}</div>
											<v-switch v-if="disabled == true" disabled prepend-icon="mdi-variable-box" :label="text_form_flag" v-model="xxxxFlag" />
											<v-switch v-if="disabled == false" prepend-icon="mdi-variable-box" clearable :label="text_form_flag" v-model="xxxxFlag" />
										</v-col>
									</v-row>
									<v-row no-gutters>
										<v-col :cols="field_md">
											<div style="font-size: small; color: red;">{{ messages.xxxxValue }}</div>
											<v-text-field v-if="disabled == true" disabled autocomplete="off" prepend-icon="mdi-variable-box" :label="text_form_value" v-model="xxxxValue" />
											<v-text-field v-if="disabled == false" autocomplete="off" prepend-icon="mdi-variable-box" clearable :label="text_form_value" v-model="xxxxValue" />
										</v-col>
									</v-row>
									<v-row no-gutters>
										<v-col :cols="field_lg">
											<div style="font-size: small; color: red;">{{ messages.remark }}</div>
											<v-text-field v-if="disabled == true" disabled autocomplete="off" prepend-icon="mdi-comment-quote" :label="text_form_remark" v-model="remark" />
											<v-text-field v-if="disabled == false" autocomplete="off" prepend-icon="mdi-comment-quote" clearable :label="text_form_remark" v-model="remark" />
										</v-col>
									</v-row>
									<v-row v-if="mode == 'drop'" no-gutters>
										<v-col><div style="color: red;">{{ text_notice_sure }}</div></v-col>
									</v-row>
									<v-row v-if="mode == 'drop'" no-gutters><v-col>&nbsp;</v-col></v-row>
									<v-row no-gutters>
										<v-col cols="auto">
											<v-btn v-if="mode == 'add'" variant="outlined" color="success" @click="action(mode)" :loading="loading" style="text-transform: none">{{ text_form_add }}</v-btn>
											<v-btn v-if="mode == 'modify'" variant="outlined" color="success" @click="action(mode)" :loading="loading" style="text-transform: none">{{ text_form_modify }}</v-btn>
											<v-btn v-if="mode == 'drop'" variant="outlined" color="success" @click="action(mode)" :loading="loading" style="text-transform: none">{{ text_form_drop }}</v-btn>
											&nbsp;<v-btn variant="outlined" color="primary" @click="cancel()" style="text-transform: none">{{ text_form_cancel }}</v-btn>
										</v-col>
									</v-row>
								</v-container>
							</v-form>
						</v-col>
					</v-row>
				</v-container>
			</div>
		</v-card>
	</v-dialog>
	<common_texts @texts_event="receive_texts" />
</template>

<script setup>
	import axios from 'axios'
	import { ref } from 'vue'
	const props = defineProps({
		mode: String,
		token: String,
		xxxxId: String,
		updateDate: String,
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

	const dialog = ref(false)
	const loading = ref(true)
	const disabled = ref(false)

	const message = ref('')
	const messages = ref({
		'xxxxName': '',
		'xxxxFlag': '',
		'xxxxValue': '',
		'remark': '',
	})
	const display = ref('')

	const text_title_add = ref('')
	const text_title_modify = ref('')
	const text_title_drop = ref('')
	const text_form_name = ref('')
	const text_form_flag = ref('')
	const text_form_value = ref('')
	const text_form_remark = ref('')
	const text_form_add = ref('')
	const text_form_modify = ref('')
	const text_form_drop = ref('')
	const text_form_cancel = ref('')
	const text_notice_sure = ref('')
	import common_texts from '../commons/texts.vue'
	function receive_texts(texts_status, texts_message, texts_value) {
		if (texts_status == 'action-ok') {
			const texts_json = texts_value
			text_title_add.value = texts_json.xxxx_dialog_title_add
			text_title_modify.value = texts_json.xxxx_dialog_title_modify
			text_title_drop.value = texts_json.xxxx_dialog_title_drop
			text_form_name.value = texts_json.xxxx_dialog_form_name
			text_form_flag.value = texts_json.xxxx_dialog_form_flag
			text_form_value.value = texts_json.xxxx_dialog_form_value
			text_form_remark.value = texts_json.xxxx_dialog_form_remark
			text_form_add.value = texts_json.xxxx_dialog_form_add
			text_form_modify.value = texts_json.xxxx_dialog_form_modify
			text_form_drop.value = texts_json.xxxx_dialog_form_drop
			text_form_cancel.value = texts_json.xxxx_dialog_form_cancel
			text_notice_sure.value = texts_json.xxxx_dialog_notice_sure
		} else {
			display.value = 'error'
			message.value = texts_message
		}
	}

	const xxxxName = ref('')
	const xxxxFlag = ref(false)
	const xxxxValue = ref('')
	const remark = ref('')

	loading.value = false
	if (display.value == '') {
		display.value = 'normal'
	}

	function cancel() {
		dialog.value = false
		return
	}

	function popup(mode) {
		loading.value = true
		dialog.value = true
		disabled.value = false
		if (mode == 'drop') {
			disabled.value = true
		}
		xxxxName.value = ''
		xxxxFlag.value = ref(false)
		xxxxValue.value = ''
		remark.value = ''
		message.value = ''
		messages.value = {
			'xxxxName': '',
			'xxxxFlag': '',
			'xxxxValue': '',
			'remark': '',
		}
		if (mode == 'modify' || mode == 'drop') {
			const xxxxId = props.xxxxId
			const updateDate = props.updateDate
			axios.get(`/xxxx/info?xxxxId=${xxxxId}&updateDate=${updateDate}`)
			.then (function(response) {
				if (response.data.status == 'action-ok') {
					xxxxName.value = response.data.messageMap.xxxxName
					xxxxFlag.value = (response.data.messageMap.xxxxFlag === 'Y' ? true : false)
					xxxxValue.value = response.data.messageMap.xxxxValue
					remark.value = response.data.messageMap.remark
				} else {
					message.value = response.data.message
				}
			})
			.catch (function(error) {
				message.value = 'Network trouble has occurred.'
			})
		}
		loading.value = false
		return
	}

	async function action(mode) {
		loading.value = true
		const formData = new FormData()
		formData.append('token', props.token)
		formData.append('xxxxName', xxxxName.value != null ? xxxxName.value : '')
		if (mode == 'add' || mode == 'modify') {
			formData.append('xxxxFlag', xxxxFlag.value ? 'Y' : 'N')
			formData.append('xxxxValue', xxxxValue.value != null ? xxxxValue.value : '')
			formData.append('remark', remark.value != null ? remark.value : '')
		}
		if (mode == 'modify' || mode == 'drop') {
			formData.append('xxxxId', props.xxxxId)
			formData.append('updateDate', props.updateDate)
		}
		await axios.post('/xxxx/' + mode, formData)
		.then (function(response) {
			if (response.data.status == 'action-ok') {
				window.location.href = '/pages/xxxx_list.html'
				return
			} else {
				message.value = response.data.message
				if (mode != 'drop') {
					messages.value = response.data.messageMap
				}
			}
		})
		.catch (function(error) {
			message.value = 'Network trouble has occurred.'
		})
		loading.value = false
		return
	}
</script>
