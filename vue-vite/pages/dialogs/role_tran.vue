<template>
	<v-icon v-if="mode == 'assign'" large @click="popup('assign')">mdi-table-plus</v-icon>
	<v-icon v-if="mode == 'unassign'" large @click="popup('unassign')">mdi-table-minus</v-icon>
	<v-dialog v-model="dialog" activator="#menu-popup" scrollable persistent no-click-animation>
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
						<v-col v-if="mode == 'assign'"><h3 class="display-1">{{ text_title_assign }}</h3></v-col>
						<v-col v-if="mode == 'unassign'"><h3 class="display-1">{{ text_title_unassign }}</h3></v-col>
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
											<v-text-field disabled autocomplete="off" prepend-icon="mdi-account-multiple" :label="text_form_roleName" v-model="roleName" />
										</v-col>
									</v-row>
									<v-row no-gutters>
										<v-col>
											<v-radio-group v-model="radio" hide-details="auto" v-for="(item, i) in items" :key="i">
												<v-radio v-if="mode == 'assign' && item.isExists == 'true'" disabled :label="item.tranName" :value="item.tranId" />
												<v-radio v-if="mode == 'assign' && item.isExists == 'false'" :label="item.tranName" :value="item.tranId" />
												<v-radio v-if="mode == 'unassign' && item.isExists == 'false'" disabled :label="item.tranName" :value="item.tranId" />
												<v-radio v-if="mode == 'unassign' && item.isExists == 'true'" :label="item.tranName" :value="item.tranId" />
											</v-radio-group>
										</v-col>
									</v-row>
									<v-row no-gutters><v-col>&nbsp;</v-col></v-row>
									<v-row v-if="mode == 'unassign'" no-gutters>
										<v-col><div style="color: red;">{{ text_notice_sure }}</div></v-col>
									</v-row>
									<v-row v-if="mode == 'unassign'" no-gutters><v-col>&nbsp;</v-col></v-row>
									<v-row no-gutters>
										<v-col cols="auto">
											<v-btn v-if="mode == 'assign'" variant="outlined" color="success" @click="action(mode)" :loading="loading" style="text-transform: none">{{ text_form_assign }}</v-btn>
											<v-btn v-if="mode == 'unassign'" variant="outlined" color="success" @click="action(mode)" :loading="loading" style="text-transform: none">{{ text_form_unassign }}</v-btn>
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
		roleId: String,
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
	const radio = ref(false)
	const items = ref([])

	const message = ref('')
	const display = ref('')

	const text_title_assign = ref('')
	const text_title_unassign = ref('')
	const text_form_assign= ref('')
	const text_form_unassign = ref('')
	const text_form_roleName = ref('')
	const text_form_cancel = ref('')
	const text_notice_sure = ref('')
	import common_texts from '../commons/texts.vue'
	function receive_texts(texts_status, texts_message, texts_value) {
		if (texts_status == 'action-ok') {
			const texts_json = texts_value
			text_title_assign.value = texts_json.role_tran_dialog_title_assign
			text_title_unassign.value = texts_json.role_tran_dialog_title_unassign
			text_form_roleName.value = texts_json.role_tran_dialog_form_roleName
			text_form_assign.value = texts_json.role_tran_dialog_form_assign
			text_form_unassign.value = texts_json.role_tran_dialog_form_unassign
			text_form_cancel.value = texts_json.role_tran_dialog_form_cancel
			text_notice_sure.value = texts_json.role_tran_dialog_notice_sure
		} else {
			display.value = 'error'
			message.value = texts_message
		}
	}

	const roleName = ref('')
	const tranId = ref('')

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
		if (mode == 'unassign') {
			disabled.value = true
		}
		message.value = ''
		roleName.value = ''
		tranId.value = ''
		radio.value = false
		items.value = []
		const roleId = props.roleId
		const updateDate = props.updateDate
		axios.get(`/role/info?roleId=${roleId}&updateDate=${updateDate}`)
		.then (function(response) {
			if (response.data.status == 'action-ok') {
				roleName.value = response.data.messageMap.roleName
			} else {
				message.value = response.data.message
				loading.value = false
				return
			}
		})
		.catch (function(error) {
			message.value = 'Network trouble has occurred.'
			loading.value = false
			return
		})
		axios.get(`/role_tran/list?roleId=${roleId}&updateDate=${updateDate}`)
		.then (function(response) {
			if (response.data.status == 'action-ok') {
				items.value = response.data.list
			} else {
				message.value = response.data.message
			}
		})
		.catch (function(error) {
			message.value = 'Network trouble has occurred.'
		})
		loading.value = false
		return
	}

	async function action(mode) {
		loading.value = true
		const formData = new FormData()
		formData.append('token', props.token)
		formData.append('roleId', props.roleId)
		formData.append('tranId', radio.value ? radio.value : '')
		await axios.post('/role_tran/' + mode, formData)
		.then (function(response) {
			if (response.data.status == 'action-ok') {
				window.location.href = '/pages/role_list.html'
				return
			} else {
				message.value = response.data.message
			}
		})
		.catch (function(error) {
			message.value = 'Network trouble has occurred.'
		})
		loading.value = false
		return
	}
</script>
