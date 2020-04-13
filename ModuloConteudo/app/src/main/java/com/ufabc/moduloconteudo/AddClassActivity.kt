package com.ufabc.moduloconteudo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout

import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.ufabc.moduloconteudo.data.AppDatabase
import com.ufabc.moduloconteudo.data.aula.Aula
import com.ufabc.moduloconteudo.data.discente_turma.DiscenteTurma
import com.ufabc.moduloconteudo.ui.configuration.ConfigurationSingleton.persistConfigModificationsOnAllViews
import com.ufabc.moduloconteudo.utilities.RA_EXTRA
import kotlinx.coroutines.runBlocking

class AddClassActivity : AppCompatActivity() {

    lateinit var btnSave : Button
    lateinit var cbFriday : CheckBox
    lateinit var cbMonday : CheckBox
    lateinit var cbSaturday : CheckBox
    lateinit var cbThursday : CheckBox
    lateinit var cbTuesday : CheckBox
    lateinit var cbWednesday : CheckBox
    lateinit var checkFriday: CheckBox
    lateinit var checkMonday: CheckBox
    lateinit var checkSaturday: CheckBox
    lateinit var checkThursday: CheckBox
    lateinit var checkTuesday: CheckBox
    lateinit var checkWednesday: CheckBox
    lateinit var clFriday: ConstraintLayout
    lateinit var clMonday: ConstraintLayout
    lateinit var clSaturday: ConstraintLayout
    lateinit var clThursday: ConstraintLayout
    lateinit var clTuesday: ConstraintLayout
    lateinit var clWednesday: ConstraintLayout
    lateinit var edtClassCode : TextInputEditText
    lateinit var edtClassName : TextInputEditText
    lateinit var edtClassroomFridayQ1 : TextInputEditText
    lateinit var edtClassroomFridayQ2 : TextInputEditText
    lateinit var edtClassroomMondayQ1 : TextInputEditText
    lateinit var edtClassroomMondayQ2 : TextInputEditText
    lateinit var edtClassroomSaturdayQ1 : TextInputEditText
    lateinit var edtClassroomSaturdayQ2 : TextInputEditText
    lateinit var edtClassroomThursdayQ1 : TextInputEditText
    lateinit var edtClassroomThursdayQ2 : TextInputEditText
    lateinit var edtClassroomTuesdayQ1 : TextInputEditText
    lateinit var edtClassroomTuesdayQ2 : TextInputEditText
    lateinit var edtClassroomWednesdayQ1 : TextInputEditText
    lateinit var edtClassroomWednesdayQ2 : TextInputEditText
    lateinit var edtHourBeginFriday : TextInputEditText
    lateinit var edtHourBeginMonday : TextInputEditText
    lateinit var edtHourBeginSaturday : TextInputEditText
    lateinit var edtHourBeginThursday : TextInputEditText
    lateinit var edtHourBeginTuesday : TextInputEditText
    lateinit var edtHourBeginWednesday : TextInputEditText
    lateinit var edtHourEndFriday: TextInputEditText
    lateinit var edtHourEndMonday: TextInputEditText
    lateinit var edtHourEndSaturday: TextInputEditText
    lateinit var edtHourEndThursday: TextInputEditText
    lateinit var edtHourEndTuesday: TextInputEditText
    lateinit var edtHourEndWednesday: TextInputEditText
    lateinit var edtPracticeTeacher: TextInputEditText
    lateinit var edtTheoryTeacher: TextInputEditText
    lateinit var llFridayQ1: LinearLayout
    lateinit var llFridayQ2: LinearLayout
    lateinit var llInfoDays: LinearLayout
    lateinit var llMondayQ1: LinearLayout
    lateinit var llMondayQ2: LinearLayout
    lateinit var llSaturdayQ1: LinearLayout
    lateinit var llSaturdayQ2: LinearLayout
    lateinit var llThursdayQ1: LinearLayout
    lateinit var llThursdayQ2: LinearLayout
    lateinit var llTuesdayQ1: LinearLayout
    lateinit var llTuesdayQ2: LinearLayout
    lateinit var llWednesdayQ1: LinearLayout
    lateinit var llWednesdayQ2: LinearLayout
    lateinit var rbPracticeFridayQ1 : RadioButton
    lateinit var rbPracticeFridayQ2 : RadioButton
    lateinit var rbPracticeMondayQ1 : RadioButton
    lateinit var rbPracticeMondayQ2 : RadioButton
    lateinit var rbPracticeSaturdayQ1 : RadioButton
    lateinit var rbPracticeSaturdayQ2 : RadioButton
    lateinit var rbPracticeThursdayQ1 : RadioButton
    lateinit var rbPracticeThursdayQ2 : RadioButton
    lateinit var rbPracticeTuesdayQ1 : RadioButton
    lateinit var rbPracticeTuesdayQ2 : RadioButton
    lateinit var rbPracticeWednesdayQ1 : RadioButton
    lateinit var rbPracticeWednesdayQ2 : RadioButton
    lateinit var rbSantoAndre: RadioButton
    lateinit var rbSaoBernardo: RadioButton
    lateinit var rbTheoryFridayQ1 : RadioButton
    lateinit var rbTheoryFridayQ2 : RadioButton
    lateinit var rbTheoryMondayQ1 : RadioButton
    lateinit var rbTheoryMondayQ2 : RadioButton
    lateinit var rbTheorySaturdayQ1 : RadioButton
    lateinit var rbTheorySaturdayQ2 : RadioButton
    lateinit var rbTheoryThursdayQ1 : RadioButton
    lateinit var rbTheoryThursdayQ2 : RadioButton
    lateinit var rbTheoryTuesdayQ1 : RadioButton
    lateinit var rbTheoryTuesdayQ2 : RadioButton
    lateinit var rbTheoryWednesdayQ1 : RadioButton
    lateinit var rbTheoryWednesdayQ2 : RadioButton
    lateinit var rgCampus: RadioGroup
    lateinit var rgClassTypeFridayQ1 : RadioGroup
    lateinit var rgClassTypeFridayQ2 : RadioGroup
    lateinit var rgClassTypeMondayQ1 : RadioGroup
    lateinit var rgClassTypeMondayQ2 : RadioGroup
    lateinit var rgClassTypeSaturdayQ1 : RadioGroup
    lateinit var rgClassTypeSaturdayQ2 : RadioGroup
    lateinit var rgClassTypeThursdayQ1 : RadioGroup
    lateinit var rgClassTypeThursdayQ2 : RadioGroup
    lateinit var rgClassTypeTuesdayQ1 : RadioGroup
    lateinit var rgClassTypeTuesdayQ2 : RadioGroup
    lateinit var rgClassTypeWednesdayQ1 : RadioGroup
    lateinit var rgClassTypeWednesdayQ2 : RadioGroup
    lateinit var swtFridayQ1: Switch
    lateinit var swtFridayQ2: Switch
    lateinit var swtMondayQ1: Switch
    lateinit var swtMondayQ2: Switch
    lateinit var swtSaturdayQ1: Switch
    lateinit var swtSaturdayQ2: Switch
    lateinit var swtThursdayQ1: Switch
    lateinit var swtThursdayQ2: Switch
    lateinit var swtTuesdayQ1: Switch
    lateinit var swtTuesdayQ2: Switch
    lateinit var swtWednesdayQ1: Switch
    lateinit var swtWednesdayQ2: Switch
    lateinit var tilClassCode : TextInputLayout
    lateinit var tilClassName : TextInputLayout
    lateinit var tilClassroomFridayQ1 : TextInputLayout
    lateinit var tilClassroomFridayQ2 : TextInputLayout
    lateinit var tilClassroomMondayQ1 : TextInputLayout
    lateinit var tilClassroomMondayQ2 : TextInputLayout
    lateinit var tilClassroomSaturdayQ1 : TextInputLayout
    lateinit var tilClassroomSaturdayQ2 : TextInputLayout
    lateinit var tilClassroomThursdayQ1 : TextInputLayout
    lateinit var tilClassroomThursdayQ2 : TextInputLayout
    lateinit var tilClassroomTuesdayQ1 : TextInputLayout
    lateinit var tilClassroomTuesdayQ2 : TextInputLayout
    lateinit var tilClassroomWednesdayQ1 : TextInputLayout
    lateinit var tilClassroomWednesdayQ2 : TextInputLayout
    lateinit var tilHourBeginFriday : TextInputLayout
    lateinit var tilHourBeginMonday : TextInputLayout
    lateinit var tilHourBeginSaturday : TextInputLayout
    lateinit var tilHourBeginThursday : TextInputLayout
    lateinit var tilHourBeginTuesday : TextInputLayout
    lateinit var tilHourBeginWednesday : TextInputLayout
    lateinit var tilHourEndFriday: TextInputLayout
    lateinit var tilHourEndMonday: TextInputLayout
    lateinit var tilHourEndSaturday: TextInputLayout
    lateinit var tilHourEndThursday: TextInputLayout
    lateinit var tilHourEndTuesday: TextInputLayout
    lateinit var tilHourEndWednesday: TextInputLayout
    lateinit var tilPracticeTeacher : TextInputLayout
    lateinit var tilTheoryTeacher : TextInputLayout

    var classList = arrayListOf<Aula>()
    var studentRA = ""
    var week_days : Array<String> = arrayOf()

    /*
        TODOS:
        1 - Fazer checagem de campos para adicionar disciplina corretamente
        2 - Botão para remover disciplina
        3 - Adicionar Campus em Aula

     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_class)

        persistConfigModificationsOnAllViews(getWindow().getDecorView().getRootView(), null)


        studentRA = intent?.getStringExtra(RA_EXTRA).toString()
        week_days = resources.getStringArray(R.array.week_days)
        bindComponents()
        setClickEvents()
    }

    private fun setClickEvents() {
        setCheckBoxListener(checkMonday, clMonday)
        setSwitchClickListener(swtMondayQ1, llMondayQ1)
        setSwitchClickListener(swtMondayQ2, llMondayQ2)

        setCheckBoxListener(checkTuesday, clTuesday)
        setSwitchClickListener(swtTuesdayQ1, llTuesdayQ1)
        setSwitchClickListener(swtTuesdayQ2, llTuesdayQ2)

        setCheckBoxListener(checkWednesday, clWednesday)
        setSwitchClickListener(swtWednesdayQ1, llWednesdayQ1)
        setSwitchClickListener(swtWednesdayQ2, llWednesdayQ2)

        setCheckBoxListener(checkThursday, clThursday)
        setSwitchClickListener(swtThursdayQ1, llThursdayQ1)
        setSwitchClickListener(swtThursdayQ2, llThursdayQ2)

        setCheckBoxListener(checkFriday, clFriday)
        setSwitchClickListener(swtFridayQ1, llFridayQ1)
        setSwitchClickListener(swtFridayQ2, llFridayQ2)

        setCheckBoxListener(checkSaturday, clSaturday)
        setSwitchClickListener(swtSaturdayQ1, llSaturdayQ1)
        setSwitchClickListener(swtSaturdayQ2, llSaturdayQ2)

        //Button click event
        btnSave.setOnClickListener {
            if(generalInformationIsOk()) {
                if (createListOfClassesIsOk()) {
                    runBlocking {
                        addClasses(it)
                    }
                    finish()
                }
            }
        }
    }

    private fun textInputLayoutHasError(textInputLayout: TextInputLayout) : Boolean {

        if(textInputLayout.editText!!.text.trim().isEmpty()) {
            createAlertDialog(this, "OK", "Erro ao adicionar disciplina", "O campo \"${textInputLayout.hint}\" não pode ser vazio.")
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = resources.getString(R.string.is_empty_error)
            return true
        }

        textInputLayout.isErrorEnabled = false
        return false
    }

    private fun radioGroupHasError(radioGroup: RadioGroup, name : String) : Boolean {
        if(radioGroup.checkedRadioButtonId == -1) {
            createAlertDialog(this, "OK", "Erro ao adicionar disciplina", "Você deve selecionar uma opção em: $name.")
            return true
        }
        return false
    }


    private fun generalInformationIsOk(): Boolean {
        var noError = true

        when {
            textInputLayoutHasError(tilClassCode)-> {
                noError = false
            }
            textInputLayoutHasError(tilClassName)-> {
                noError = false
            }
            radioGroupHasError(rgCampus, "Campus") -> {
                noError = false
            }
        }
        return noError
    }

    private fun createAlertDialog(context: Context, positive: String, title: String, message: String) {
        MaterialAlertDialogBuilder(context)
            .setPositiveButton(positive){_,_ ->}
            .setTitle(title)
            .setMessage(message)
            .show()
    }
    private suspend fun addClasses(it: View) {
        val database = AppDatabase.getInstance(it.context)
        database.discenteTurmaDao().insertSingle(DiscenteTurma(studentRA, edtClassCode.text.toString()))
        database.aulaDao().insertAulaList(classList)
    }

    private fun createListOfClassesIsOk() : Boolean {
        classList.clear()
        var noError = true
        if(cbMonday.isChecked)
            if(!textInputLayoutHasError(tilHourBeginMonday))
                if(!textInputLayoutHasError(tilHourEndMonday)) {
                    noError = addDayClass(
                        1, tilHourBeginMonday, tilHourEndMonday,
                        swtMondayQ1, tilClassroomMondayQ1, rgClassTypeMondayQ1, rbTheoryMondayQ1, rbPracticeMondayQ1,
                        swtMondayQ2, tilClassroomMondayQ2, rgClassTypeMondayQ2, rbTheoryMondayQ2, rbPracticeMondayQ2
                    )
                }
                else noError = false
            else noError = false

        if(cbTuesday.isChecked)
            if(!textInputLayoutHasError(tilHourBeginTuesday))
                if(!textInputLayoutHasError(tilHourEndTuesday)) {
                    noError = addDayClass(
                        2, tilHourBeginTuesday, tilHourEndTuesday,
                        swtTuesdayQ1, tilClassroomTuesdayQ1, rgClassTypeTuesdayQ1, rbTheoryTuesdayQ1, rbPracticeTuesdayQ1,
                        swtTuesdayQ2, tilClassroomTuesdayQ2, rgClassTypeTuesdayQ2, rbTheoryTuesdayQ2, rbPracticeTuesdayQ2
                    )
                }
                else noError = false
            else noError = false
        if(cbWednesday.isChecked)
            if(textInputLayoutHasError(tilHourBeginWednesday))
                if(textInputLayoutHasError(tilHourEndWednesday)) {
                    noError = addDayClass(
                        3, tilHourBeginWednesday, tilHourEndWednesday,
                        swtWednesdayQ1, tilClassroomWednesdayQ1, rgClassTypeWednesdayQ1, rbTheoryWednesdayQ1, rbPracticeWednesdayQ1,
                        swtWednesdayQ2, tilClassroomWednesdayQ2, rgClassTypeWednesdayQ2, rbTheoryWednesdayQ2, rbPracticeWednesdayQ2
                    )
                }
                else noError = false
            else noError = false
        if(cbThursday.isChecked)
            if(textInputLayoutHasError(tilHourBeginThursday))
                if(textInputLayoutHasError(tilHourEndThursday)) {
                    noError = addDayClass(
                        4, tilHourBeginThursday, tilHourEndThursday,
                        swtThursdayQ1, tilClassroomThursdayQ1, rgClassTypeThursdayQ1, rbTheoryThursdayQ1, rbPracticeThursdayQ1,
                        swtThursdayQ2, tilClassroomThursdayQ2, rgClassTypeThursdayQ2, rbTheoryThursdayQ2, rbPracticeThursdayQ2
                    )
                }
                else noError = false
            else noError = false
        if(cbFriday.isChecked)
            if(textInputLayoutHasError(tilHourBeginFriday))
                if(textInputLayoutHasError(tilHourEndFriday)) {
                    noError = addDayClass(
                        5, tilHourBeginFriday, tilHourEndFriday,
                        swtFridayQ1, tilClassroomFridayQ1, rgClassTypeFridayQ1, rbTheoryFridayQ1, rbPracticeFridayQ1,
                        swtFridayQ2, tilClassroomFridayQ2, rgClassTypeFridayQ2, rbTheoryFridayQ2, rbPracticeFridayQ2
                    )
                }
                else noError = false
            else noError = false
        if(cbSaturday.isChecked)
            if(textInputLayoutHasError(tilHourBeginSaturday))
                if(textInputLayoutHasError(tilHourEndSaturday)) {
                    noError = addDayClass(
                        6, tilHourBeginSaturday, tilHourEndSaturday,
                        swtSaturdayQ1, tilClassroomSaturdayQ1, rgClassTypeSaturdayQ1, rbTheorySaturdayQ1, rbPracticeSaturdayQ1,
                        swtSaturdayQ2, tilClassroomSaturdayQ2, rgClassTypeSaturdayQ2, rbTheorySaturdayQ2, rbPracticeSaturdayQ2
                    )
                }
                else noError = false
            else noError = false

        return noError
    }

    private fun addDayClass (
        dayId: Int, tilHourBegin: TextInputLayout, tilHourEnd: TextInputLayout,
        swtQ1: Switch, tilClassroomQ1: TextInputLayout, rgClassTypeQ1: RadioGroup, rbTheoryQ1: RadioButton, rbPracticeQ1: RadioButton,
        swtQ2: Switch, tilClassroomQ2: TextInputLayout, rgClassTypeQ2: RadioGroup, rbTheoryQ2: RadioButton, rbPracticeQ2: RadioButton
    ) : Boolean {
        if(swtQ1.isChecked)
            if(!textInputLayoutHasError(tilClassroomQ1))
                if(!radioGroupHasError(rgClassTypeQ1, "Tipo de aula ${week_days[dayId]} Quinzenal 1")) {
                    classList.add(
                        Aula(
                            codigo_sie = edtClassCode.text.toString(),
                            horario_inicio = tilHourBegin.editText!!.text.toString().toInt(),
                            horario_fim = tilHourBegin.editText!!.text.toString().toInt(),
                            id_dia_semana = dayId,
                            nome_turma = edtClassName.text.toString() + " (${if (rbSantoAndre.isChecked)"Santo André" else "São Bernardo"})",
                            id_tipo_aula = if (rbTheoryQ1.isChecked) 0 else 1,
                            nome_doscente = if (rbTheoryQ1.isChecked) edtTheoryTeacher.text.toString() else edtPracticeTeacher.text.toString(),
                            sobrenome_doscente = "",
                            quinzenal_1 = true,
                            quinzenal_2 = false,
                            sala = tilClassroomQ1.editText!!.text.toString()
                        )
                    )
                }
                else return false
            else return false
        if(swtQ2.isChecked)
            if(!textInputLayoutHasError(tilClassroomQ2))
                if(!radioGroupHasError(rgClassTypeQ2, "Tipo de aula ${week_days[dayId]} Quinzenal 2")) {
                    classList.add(
                        Aula(
                            codigo_sie = edtClassCode.text.toString(),
                            horario_inicio = tilHourBegin.editText!!.text.toString().toInt(),
                            horario_fim = tilHourEnd.editText!!.text.toString().toInt(),
                            id_dia_semana = dayId,
                            nome_turma = edtClassName.text.toString() + " (${if (rbSantoAndre.isChecked)"Santo André" else "São Bernardo"})",
                            id_tipo_aula = if (rbTheoryQ2.isChecked) 0 else 1,
                            nome_doscente = if (rbTheoryQ2.isChecked) edtTheoryTeacher.text.toString() else edtPracticeTeacher.text.toString(),
                            sobrenome_doscente = "",
                            quinzenal_1 = false,
                            quinzenal_2 = true,
                            sala = tilClassroomQ2.editText!!.text.toString()
                        )
                    )
                }
                else return false
            else return false

        return true
    }

    private fun setSwitchClickListener(switch: Switch, linearLayout: LinearLayout) {
        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                linearLayout.visibility = View.VISIBLE
            } else {
                linearLayout.visibility = View.GONE
            }
        }
    }

    private fun setCheckBoxListener(checkBox: CheckBox, constraintLayout: ConstraintLayout) {
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                constraintLayout.visibility = View.VISIBLE
            } else {
                constraintLayout.visibility = View.GONE
            }
            if (checkMonday.isChecked || checkTuesday.isChecked || checkWednesday.isChecked || checkThursday.isChecked ||
                checkFriday.isChecked || checkSaturday.isChecked
            )
                llInfoDays.visibility = View.VISIBLE
            else llInfoDays.visibility = View.GONE
        }
    }

    private fun bindComponents() {
        btnSave = findViewById(R.id.add_btnSave)
        cbFriday  = findViewById(R.id.add_cbFriday)
        cbMonday = findViewById(R.id.add_cbMonday)
        cbSaturday  = findViewById(R.id.add_cbSaturday)
        cbThursday  = findViewById(R.id.add_cbThursday)
        cbTuesday  = findViewById(R.id.add_cbTuesday)
        cbWednesday = findViewById(R.id.add_cbWednesday)
        checkFriday = findViewById(R.id.add_cbFriday)
        checkMonday = findViewById(R.id.add_cbMonday)
        checkSaturday = findViewById(R.id.add_cbSaturday)
        checkThursday = findViewById(R.id.add_cbThursday)
        checkTuesday = findViewById(R.id.add_cbTuesday)
        checkWednesday = findViewById(R.id.add_cbWednesday)
        clFriday = findViewById(R.id.add_includeFriday)
        clMonday = findViewById(R.id.add_includeMonday)
        clSaturday = findViewById(R.id.add_includeSaturday)
        clThursday = findViewById(R.id.add_includeThursday)
        clTuesday = findViewById(R.id.add_includeTuesday)
        clWednesday = findViewById(R.id.add_includeWednesday)
        edtClassCode = findViewById(R.id.add_tietClassCode)
        edtClassName = findViewById(R.id.add_tietClassName)
        edtClassroomFridayQ1  = findViewById(R.id.add_edtClassroomFridayQ1)
        edtClassroomFridayQ1  = findViewById(R.id.add_edtClassroomFridayQ1)
        edtClassroomFridayQ2  = findViewById(R.id.add_edtClassroomFridayQ2)
        edtClassroomFridayQ2  = findViewById(R.id.add_edtClassroomFridayQ2)
        edtClassroomMondayQ1  = findViewById(R.id.add_edtClassroomMondayQ1)
        edtClassroomMondayQ2  = findViewById(R.id.add_edtClassroomMondayQ2)
        edtClassroomThursdayQ1  = findViewById(R.id.add_edtClassroomThursdayQ1)
        edtClassroomThursdayQ2  = findViewById(R.id.add_edtClassroomThursdayQ2)
        edtClassroomTuesdayQ1  = findViewById(R.id.add_edtClassroomTuesdayQ1)
        edtClassroomTuesdayQ2  = findViewById(R.id.add_edtClassroomTuesdayQ2)
        edtClassroomWednesdayQ1  = findViewById(R.id.add_edtClassroomWednesdayQ1)
        edtClassroomWednesdayQ2  = findViewById(R.id.add_edtClassroomWednesdayQ2)
        edtHourBeginFriday  = findViewById(R.id.add_edtBeginHourFriday)
        edtHourBeginFriday  = findViewById(R.id.add_edtBeginHourFriday)
        edtHourBeginFriday = findViewById(R.id.add_edtBeginHourFriday)
        edtHourBeginFriday = findViewById(R.id.add_edtBeginHourFriday)
        edtHourBeginMonday = findViewById(R.id.add_edtBeginHourMonday)
        edtHourBeginThursday  = findViewById(R.id.add_edtBeginHourThursday)
        edtHourBeginThursday = findViewById(R.id.add_edtBeginHourThursday)
        edtHourBeginTuesday  = findViewById(R.id.add_edtBeginHourTuesday)
        edtHourBeginTuesday  = findViewById(R.id.add_edtBeginHourTuesday)
        edtHourBeginTuesday = findViewById(R.id.add_edtBeginHourTuesday)
        edtHourBeginWednesday  = findViewById(R.id.add_edtBeginHourWednesday)
        edtHourBeginWednesday = findViewById(R.id.add_edtBeginHourWednesday)
        edtHourEndFriday = findViewById(R.id.add_edtEndHourFriday)
        edtHourEndFriday = findViewById(R.id.add_edtEndHourFriday)
        edtHourEndMonday = findViewById(R.id.add_edtEndHourMonday)
        edtHourEndThursday = findViewById(R.id.add_edtEndHourThursday)
        edtHourEndTuesday = findViewById(R.id.add_edtEndHourTuesday)
        edtHourEndWednesday = findViewById(R.id.add_edtEndHourWednesday)
        edtPracticeTeacher = findViewById(R.id.add_tietPTeacherName)
        edtTheoryTeacher = findViewById(R.id.add_tietTTeacherName)
        llFridayQ1 = findViewById(R.id.add_llFridayQ1)
        llFridayQ2 = findViewById(R.id.add_llFridayQ2)
        llInfoDays = findViewById(R.id.add_llInfoDays)
        llMondayQ1 = findViewById(R.id.add_llMondayQ1)
        llMondayQ2 = findViewById(R.id.add_llMondayQ2)
        llSaturdayQ1 = findViewById(R.id.add_llSaturdayQ1)
        llSaturdayQ2 = findViewById(R.id.add_llSaturdayQ2)
        llThursdayQ1 = findViewById(R.id.add_llThursdayQ1)
        llThursdayQ2 = findViewById(R.id.add_llThursdayQ2)
        llTuesdayQ1 = findViewById(R.id.add_llTuesdayQ1)
        llTuesdayQ2 = findViewById(R.id.add_llTuesdayQ2)
        llWednesdayQ1 = findViewById(R.id.add_llWednesdayQ1)
        llWednesdayQ2 = findViewById(R.id.add_llWednesdayQ2)
        rbPracticeFridayQ1  = findViewById(R.id.add_rgPracticeFridayQ1)
        rbPracticeFridayQ1  = findViewById(R.id.add_rgPracticeFridayQ1)
        rbPracticeFridayQ2  = findViewById(R.id.add_rgPracticeFridayQ2)
        rbPracticeFridayQ2  = findViewById(R.id.add_rgPracticeFridayQ2)
        rbPracticeMondayQ1  = findViewById(R.id.add_rgPracticeMondayQ1)
        rbPracticeMondayQ2  = findViewById(R.id.add_rgPracticeMondayQ2)
        rbPracticeThursdayQ1  = findViewById(R.id.add_rgPracticeThursdayQ1)
        rbPracticeThursdayQ2  = findViewById(R.id.add_rgPracticeThursdayQ2)
        rbPracticeTuesdayQ1  = findViewById(R.id.add_rgPracticeTuesdayQ1)
        rbPracticeTuesdayQ2  = findViewById(R.id.add_rgPracticeTuesdayQ2)
        rbPracticeWednesdayQ1  = findViewById(R.id.add_rgPracticeWednesdayQ1)
        rbPracticeWednesdayQ2  = findViewById(R.id.add_rgPracticeWednesdayQ2)
        rbSantoAndre = findViewById(R.id.add_rbSantoAndre)
        rbSaoBernardo = findViewById(R.id.add_rbSaoBernardo)
        rbTheoryFridayQ1  = findViewById(R.id.add_rgTheoryFridayQ1)
        rbTheoryFridayQ1  = findViewById(R.id.add_rgTheoryFridayQ1)
        rbTheoryFridayQ2  = findViewById(R.id.add_rgTheoryFridayQ2)
        rbTheoryFridayQ2  = findViewById(R.id.add_rgTheoryFridayQ2)
        rbTheoryMondayQ1  = findViewById(R.id.add_rgTheoryMondayQ1)
        rbTheoryMondayQ2  = findViewById(R.id.add_rgTheoryMondayQ2)
        rbTheoryThursdayQ1  = findViewById(R.id.add_rgTheoryThursdayQ1)
        rbTheoryThursdayQ2  = findViewById(R.id.add_rgTheoryThursdayQ2)
        rbTheoryTuesdayQ1  = findViewById(R.id.add_rgTheoryTuesdayQ1)
        rbTheoryTuesdayQ2  = findViewById(R.id.add_rgTheoryTuesdayQ2)
        rbTheoryWednesdayQ1  = findViewById(R.id.add_rgTheoryWednesdayQ1)
        rbTheoryWednesdayQ2  = findViewById(R.id.add_rgTheoryWednesdayQ2)
        rgCampus = findViewById(R.id.add_rgCampus)
        rgClassTypeFridayQ1  = findViewById(R.id.add_rgClassTypeFridayQ1)
        rgClassTypeFridayQ1  = findViewById(R.id.add_rgClassTypeFridayQ1)
        rgClassTypeFridayQ2  = findViewById(R.id.add_rgClassTypeFridayQ2)
        rgClassTypeFridayQ2  = findViewById(R.id.add_rgClassTypeFridayQ2)
        rgClassTypeMondayQ1  = findViewById(R.id.add_rgClassTypeMondayQ1)
        rgClassTypeMondayQ2  = findViewById(R.id.add_rgClassTypeMondayQ2)
        rgClassTypeThursdayQ1  = findViewById(R.id.add_rgClassTypeThursdayQ1)
        rgClassTypeThursdayQ2  = findViewById(R.id.add_rgClassTypeThursdayQ2)
        rgClassTypeTuesdayQ1  = findViewById(R.id.add_rgClassTypeTuesdayQ1)
        rgClassTypeTuesdayQ2  = findViewById(R.id.add_rgClassTypeTuesdayQ2)
        rgClassTypeWednesdayQ1  = findViewById(R.id.add_rgClassTypeWednesdayQ1)
        rgClassTypeWednesdayQ2  = findViewById(R.id.add_rgClassTypeWednesdayQ2)
        swtFridayQ1 = findViewById(R.id.add_swtFridayQ1)
        swtFridayQ2 = findViewById(R.id.add_swtFridayQ2)
        swtMondayQ1 = findViewById(R.id.add_swtMondayQ1)
        swtMondayQ2 = findViewById(R.id.add_swtMondayQ2)
        swtSaturdayQ1 = findViewById(R.id.add_swtSaturdayQ1)
        swtSaturdayQ2 = findViewById(R.id.add_swtSaturdayQ2)
        swtThursdayQ1 = findViewById(R.id.add_swtThursdayQ1)
        swtThursdayQ2 = findViewById(R.id.add_swtThursdayQ2)
        swtTuesdayQ1 = findViewById(R.id.add_swtTuesdayQ1)
        swtTuesdayQ2 = findViewById(R.id.add_swtTuesdayQ2)
        swtWednesdayQ1 = findViewById(R.id.add_swtWednesdayQ1)
        swtWednesdayQ2 = findViewById(R.id.add_swtWednesdayQ2)
        tilClassCode = findViewById(R.id.add_tilClassCode)
        tilClassName = findViewById(R.id.add_tilClassName)
        tilPracticeTeacher = findViewById(R.id.add_tilPTeacherName)
        tilTheoryTeacher = findViewById(R.id.add_tilTTeacherName)
        tilHourBeginMonday = findViewById(R.id.add_tilHourBeginMonday)
        tilHourEndMonday = findViewById(R.id.add_tilHourEndMonday)
        tilClassroomMondayQ1 = findViewById(R.id.add_tilClassroomMondayQ1)
        tilClassroomMondayQ2 = findViewById(R.id.add_tilClassroomMondayQ2)
        tilHourBeginTuesday = findViewById(R.id.add_tilHourBeginTuesday)
        tilHourEndTuesday = findViewById(R.id.add_tilHourEndTuesday)
        tilClassroomTuesdayQ1 = findViewById(R.id.add_tilClassroomTuesdayQ1)
        tilClassroomTuesdayQ2 = findViewById(R.id.add_tilClassroomTuesdayQ2)
        tilHourBeginWednesday = findViewById(R.id.add_tilHourBeginWednesday)
        tilHourEndWednesday = findViewById(R.id.add_tilHourEndWednesday)
        tilClassroomWednesdayQ1 = findViewById(R.id.add_tilClassroomWednesdayQ1)
        tilClassroomWednesdayQ2 = findViewById(R.id.add_tilClassroomWednesdayQ2)
        tilHourBeginThursday = findViewById(R.id.add_tilHourBeginThursday)
        tilHourEndThursday = findViewById(R.id.add_tilHourEndThursday)
        tilClassroomThursdayQ1 = findViewById(R.id.add_tilClassroomThursdayQ1)
        tilClassroomThursdayQ2 = findViewById(R.id.add_tilClassroomThursdayQ2)
        tilHourBeginFriday = findViewById(R.id.add_tilHourBeginFriday)
        tilHourEndFriday = findViewById(R.id.add_tilHourEndFriday)
        tilClassroomFridayQ1 = findViewById(R.id.add_tilClassroomFridayQ1)
        tilClassroomFridayQ2 = findViewById(R.id.add_tilClassroomFridayQ2)
        tilHourBeginSaturday = findViewById(R.id.add_tilHourBeginSaturday)
        tilHourEndSaturday = findViewById(R.id.add_tilHourEndSaturday)
        tilClassroomSaturdayQ1 = findViewById(R.id.add_tilClassroomSaturdayQ1)
        tilClassroomSaturdayQ2 = findViewById(R.id.add_tilClassroomSaturdayQ2)
    }
}