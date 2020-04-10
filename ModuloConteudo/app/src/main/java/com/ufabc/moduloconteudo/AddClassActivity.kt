package com.ufabc.moduloconteudo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.ufabc.moduloconteudo.data.AppDatabase
import com.ufabc.moduloconteudo.data.aula.Aula
import com.ufabc.moduloconteudo.data.discente_turma.DiscenteTurma
import com.ufabc.moduloconteudo.utilities.RA_EXTRA
import kotlinx.coroutines.runBlocking

class AddClassActivity : AppCompatActivity() {

    lateinit var tilClassCode : TextInputLayout
    lateinit var edtClassCode : TextInputEditText
    lateinit var tilClassName : TextInputLayout
    lateinit var edtClassName : TextInputEditText
    lateinit var tilTheoryTeacher : TextInputLayout
    lateinit var edtTheoryTeacher: TextInputEditText
    lateinit var tilPracticeTeacher : TextInputLayout
    lateinit var edtPracticeTeacher: TextInputEditText
    lateinit var rgCampus: RadioGroup
    lateinit var rbSantoAndre: RadioButton
    lateinit var rbSaoBernardo: RadioButton
    lateinit var cbMonday : CheckBox
    lateinit var cbTuesday : CheckBox
    lateinit var cbWednesday : CheckBox
    lateinit var cbThursday : CheckBox
    lateinit var cbFriday : CheckBox
    lateinit var cbSaturday : CheckBox
    lateinit var edtHourBeginMonday : TextInputEditText
    lateinit var edtHourEndMonday: TextInputEditText
    lateinit var edtClassroomMondayQ1 : TextInputEditText
    lateinit var rgClassTypeMondayQ1 : RadioGroup
    lateinit var rbTheoryMondayQ1 : RadioButton
    lateinit var rbPracticeMondayQ1 : RadioButton
    lateinit var edtClassroomMondayQ2 : TextInputEditText
    lateinit var rgClassTypeMondayQ2 : RadioGroup
    lateinit var rbTheoryMondayQ2 : RadioButton
    lateinit var rbPracticeMondayQ2 : RadioButton
    lateinit var edtHourBeginTuesday : TextInputEditText
    lateinit var edtHourEndTuesday: TextInputEditText
    lateinit var edtClassroomTuesdayQ1 : TextInputEditText
    lateinit var rgClassTypeTuesdayQ1 : RadioGroup
    lateinit var rbTheoryTuesdayQ1 : RadioButton
    lateinit var rbPracticeTuesdayQ1 : RadioButton
    lateinit var edtClassroomTuesdayQ2 : TextInputEditText
    lateinit var rgClassTypeTuesdayQ2 : RadioGroup
    lateinit var rbTheoryTuesdayQ2 : RadioButton
    lateinit var rbPracticeTuesdayQ2 : RadioButton
    lateinit var edtHourBeginWednesday : TextInputEditText
    lateinit var edtHourEndWednesday: TextInputEditText
    lateinit var edtClassroomWednesdayQ1 : TextInputEditText
    lateinit var rgClassTypeWednesdayQ1 : RadioGroup
    lateinit var rbTheoryWednesdayQ1 : RadioButton
    lateinit var rbPracticeWednesdayQ1 : RadioButton
    lateinit var edtClassroomWednesdayQ2 : TextInputEditText
    lateinit var rgClassTypeWednesdayQ2 : RadioGroup
    lateinit var rbTheoryWednesdayQ2 : RadioButton
    lateinit var rbPracticeWednesdayQ2 : RadioButton
    lateinit var edtHourBeginThursday : TextInputEditText
    lateinit var edtHourEndThursday: TextInputEditText
    lateinit var edtClassroomThursdayQ1 : TextInputEditText
    lateinit var rgClassTypeThursdayQ1 : RadioGroup
    lateinit var rbTheoryThursdayQ1 : RadioButton
    lateinit var rbPracticeThursdayQ1 : RadioButton
    lateinit var edtClassroomThursdayQ2 : TextInputEditText
    lateinit var rgClassTypeThursdayQ2 : RadioGroup
    lateinit var rbTheoryThursdayQ2 : RadioButton
    lateinit var rbPracticeThursdayQ2 : RadioButton
    lateinit var edtHourBeginFriday : TextInputEditText
    lateinit var edtHourEndFriday: TextInputEditText
    lateinit var edtClassroomFridayQ1 : TextInputEditText
    lateinit var rgClassTypeFridayQ1 : RadioGroup
    lateinit var rbTheoryFridayQ1 : RadioButton
    lateinit var rbPracticeFridayQ1 : RadioButton
    lateinit var edtClassroomFridayQ2 : TextInputEditText
    lateinit var rgClassTypeFridayQ2 : RadioGroup
    lateinit var rbTheoryFridayQ2 : RadioButton
    lateinit var rbPracticeFridayQ2 : RadioButton
    lateinit var edtHourBeginSaturday : TextInputEditText
    lateinit var edtHourEndSaturday: TextInputEditText
    lateinit var edtClassroomSaturdayQ1 : TextInputEditText
    lateinit var rgClassTypeSaturdayQ1 : RadioGroup
    lateinit var rbTheorySaturdayQ1 : RadioButton
    lateinit var rbPracticeSaturdayQ1 : RadioButton
    lateinit var edtClassroomSaturdayQ2 : TextInputEditText
    lateinit var rgClassTypeSaturdayQ2 : RadioGroup
    lateinit var rbTheorySaturdayQ2 : RadioButton
    lateinit var rbPracticeSaturdayQ2 : RadioButton
    lateinit var btnSave : Button
    lateinit var checkMonday: CheckBox
    lateinit var checkTuesday: CheckBox
    lateinit var checkWednesday: CheckBox
    lateinit var checkThursday: CheckBox
    lateinit var checkFriday: CheckBox
    lateinit var checkSaturday: CheckBox
    lateinit var llInfoDays: LinearLayout
    lateinit var clMonday: ConstraintLayout
    lateinit var clTuesday: ConstraintLayout
    lateinit var clWednesday: ConstraintLayout
    lateinit var clThursday: ConstraintLayout
    lateinit var clFriday: ConstraintLayout
    lateinit var clSaturday: ConstraintLayout
    lateinit var llMondayQ1: LinearLayout
    lateinit var llMondayQ2: LinearLayout
    lateinit var llTuesdayQ1: LinearLayout
    lateinit var llTuesdayQ2: LinearLayout
    lateinit var llWednesdayQ1: LinearLayout
    lateinit var llWednesdayQ2: LinearLayout
    lateinit var llThursdayQ1: LinearLayout
    lateinit var llThursdayQ2: LinearLayout
    lateinit var llFridayQ1: LinearLayout
    lateinit var llFridayQ2: LinearLayout
    lateinit var llSaturdayQ1: LinearLayout
    lateinit var llSaturdayQ2: LinearLayout
    lateinit var swtMondayQ1: Switch
    lateinit var swtMondayQ2: Switch
    lateinit var swtTuesdayQ1: Switch
    lateinit var swtTuesdayQ2: Switch
    lateinit var swtWednesdayQ1: Switch
    lateinit var swtWednesdayQ2: Switch
    lateinit var swtThursdayQ1: Switch
    lateinit var swtThursdayQ2: Switch
    lateinit var swtFridayQ1: Switch
    lateinit var swtFridayQ2: Switch
    lateinit var swtSaturdayQ1: Switch
    lateinit var swtSaturdayQ2: Switch

    var classList = arrayListOf<Aula>()
    var studentRA = ""

    /*
        TODOS:
        1 - Fazer checagem de campos para adicionar disciplina corretamente
        2 - Botão para remover disciplina
        3 - Adicionar Campus em Aula

     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_class)
        studentRA = intent?.getStringExtra(RA_EXTRA).toString()
        bindComponents()
        setClickEvents()
        setErrorChecks()
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

    private fun setErrorChecks() {
        textInputLayoutErrorCheck(tilClassCode)
        textInputLayoutErrorCheck(tilClassName)
        textInputLayoutErrorCheck(tilTheoryTeacher)
        textInputLayoutErrorCheck(tilPracticeTeacher)
    }

    private fun textInputLayoutErrorCheck(textInputLayout: TextInputLayout) {
        textInputLayout.editText!!.setOnFocusChangeListener { v, hasFocus ->
            if(!hasFocus && textInputLayout.editText!!.text.trim().isEmpty()) {
                textInputLayout.isErrorEnabled = true
                textInputLayout.error = resources.getString(R.string.is_empty_error)
            } else {
                textInputLayout.isErrorEnabled = false
            }
        }
        textInputLayout.editText!!.text.trim()
    }

    private fun generalInformationIsOk(): Boolean {
        var noError = true
        return noError
    }

    private suspend fun addClasses(it: View) {
        val database = AppDatabase.getInstance(it.context)
        database.discenteTurmaDao().insertSingle(DiscenteTurma(studentRA, edtClassCode.text.toString()))
        database.aulaDao().insertAulaList(classList)
    }

    private fun createListOfClassesIsOk() : Boolean {
        classList.clear()
        if(cbMonday.isChecked) {
            addDayClass(
                1, edtHourBeginMonday, edtHourEndMonday,
                swtMondayQ1, edtClassroomMondayQ1, rgClassTypeMondayQ1, rbTheoryMondayQ1, rbPracticeMondayQ1,
                swtMondayQ2, edtClassroomMondayQ2, rgClassTypeMondayQ2, rbTheoryMondayQ2, rbPracticeMondayQ2
            )
        }
        if(cbTuesday.isChecked) {
            addDayClass(
                2, edtHourBeginTuesday, edtHourEndTuesday,
                swtTuesdayQ1, edtClassroomTuesdayQ1, rgClassTypeTuesdayQ1, rbTheoryTuesdayQ1, rbPracticeTuesdayQ1,
                swtTuesdayQ2, edtClassroomTuesdayQ2, rgClassTypeTuesdayQ2, rbTheoryTuesdayQ2, rbPracticeTuesdayQ2
            )
        }
        if(cbWednesday.isChecked) {
            addDayClass(
                3, edtHourBeginWednesday, edtHourEndWednesday,
                swtWednesdayQ1, edtClassroomWednesdayQ1, rgClassTypeWednesdayQ1, rbTheoryWednesdayQ1, rbPracticeWednesdayQ1,
                swtWednesdayQ2, edtClassroomWednesdayQ2, rgClassTypeWednesdayQ2, rbTheoryWednesdayQ2, rbPracticeWednesdayQ2
            )
        }
        if(cbThursday.isChecked) {
            addDayClass(
                4, edtHourBeginThursday, edtHourEndThursday,
                swtThursdayQ1, edtClassroomThursdayQ1, rgClassTypeThursdayQ1, rbTheoryThursdayQ1, rbPracticeThursdayQ1,
                swtThursdayQ2, edtClassroomThursdayQ2, rgClassTypeThursdayQ2, rbTheoryThursdayQ2, rbPracticeThursdayQ2
            )
        }
        if(cbFriday.isChecked) {
            addDayClass(
                5, edtHourBeginFriday, edtHourEndFriday,
                swtFridayQ1, edtClassroomFridayQ1, rgClassTypeFridayQ1, rbTheoryFridayQ1, rbPracticeFridayQ1,
                swtFridayQ2, edtClassroomFridayQ2, rgClassTypeFridayQ2, rbTheoryFridayQ2, rbPracticeFridayQ2
            )
        }
        if(cbSaturday.isChecked) {
            addDayClass(
                6, edtHourBeginSaturday, edtHourEndSaturday,
                swtSaturdayQ1, edtClassroomSaturdayQ1, rgClassTypeSaturdayQ1, rbTheorySaturdayQ1, rbPracticeSaturdayQ1,
                swtSaturdayQ2, edtClassroomSaturdayQ2, rgClassTypeSaturdayQ2, rbTheorySaturdayQ2, rbPracticeSaturdayQ2
            )
        }

        return true
    }

    private fun addDayClass(
        dayId: Int, edtHourBegin: TextInputEditText, edtHourEnd: TextInputEditText,
        swtQ1: Switch, edtClassroomQ1: TextInputEditText, rgClassTypeQ1: RadioGroup, rbTheoryQ1: RadioButton, rbPracticeQ1: RadioButton,
        swtQ2: Switch, edtClassroomQ2: TextInputEditText, rgClassTypeQ2: RadioGroup, rbTheoryQ2: RadioButton, rbPracticeQ2: RadioButton
    ) {
        if(swtQ1.isChecked) {
            classList.add(
                Aula(
                    codigo_sie = edtClassCode.text.toString(),
                    horario_inicio = edtHourBegin.text.toString().toInt(),
                    horario_fim = edtHourEnd.text.toString().toInt(),
                    id_dia_semana = dayId,
                    nome_turma = edtClassName.text.toString() + " (${if (rbSantoAndre.isChecked)"Santo André" else "São Bernardo"})",
                    id_tipo_aula = if (rbTheoryQ1.isChecked) 0 else 1,
                    nome_doscente = if (rbTheoryQ1.isChecked) edtTheoryTeacher.text.toString() else edtPracticeTeacher.text.toString(),
                    sobrenome_doscente = "",
                    quinzenal_1 = true,
                    quinzenal_2 = false,
                    sala = edtClassroomQ1.text.toString()
                )
            )
        }
        if(swtQ2.isChecked) {
            classList.add(
                Aula(
                    codigo_sie = edtClassCode.text.toString(),
                    horario_inicio = edtHourBegin.text.toString().toInt(),
                    horario_fim = edtHourEnd.text.toString().toInt(),
                    id_dia_semana = dayId,
                    nome_turma = edtClassName.text.toString() + " (${if (rbSantoAndre.isChecked)"Santo André" else "São Bernardo"})",
                    id_tipo_aula = if (rbTheoryQ2.isChecked) 0 else 1,
                    nome_doscente = if (rbTheoryQ2.isChecked) edtTheoryTeacher.text.toString() else edtPracticeTeacher.text.toString(),
                    sobrenome_doscente = "",
                    quinzenal_1 = false,
                    quinzenal_2 = true,
                    sala = edtClassroomQ2.text.toString()
                )
            )
        }

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
        tilClassCode = findViewById(R.id.add_tilClassCode)
        edtClassCode = findViewById(R.id.add_tietClassCode)
        tilClassName = findViewById(R.id.add_tilClassName)
        edtClassName = findViewById(R.id.add_tietClassName)
        tilTheoryTeacher = findViewById(R.id.add_tilTTeacherName)
        edtTheoryTeacher = findViewById(R.id.add_tietTTeacherName)
        tilPracticeTeacher = findViewById(R.id.add_tilPTeacherName)
        edtPracticeTeacher = findViewById(R.id.add_tietPTeacherName)
        rgCampus = findViewById(R.id.add_rgCampus)
        rbSantoAndre = findViewById(R.id.add_rbSantoAndre)
        rbSaoBernardo = findViewById(R.id.add_rbSaoBernardo)
        cbMonday = findViewById(R.id.add_cbMonday)
        cbTuesday  = findViewById(R.id.add_cbTuesday)
        cbWednesday = findViewById(R.id.add_cbWednesday)
        cbThursday  = findViewById(R.id.add_cbThursday)
        cbFriday  = findViewById(R.id.add_cbFriday)
        cbSaturday  = findViewById(R.id.add_cbSaturday)

        edtHourBeginMonday = findViewById(R.id.add_edtBeginHourMonday)
        edtHourEndMonday = findViewById(R.id.add_edtEndHourMonday)
        edtClassroomMondayQ1  = findViewById(R.id.add_edtClassroomMondayQ1)
        rgClassTypeMondayQ1  = findViewById(R.id.add_rgClassTypeMondayQ1)
        rbTheoryMondayQ1  = findViewById(R.id.add_rgTheoryMondayQ1)
        rbPracticeMondayQ1  = findViewById(R.id.add_rgPracticeMondayQ1)
        edtClassroomMondayQ2  = findViewById(R.id.add_edtClassroomMondayQ2)
        rgClassTypeMondayQ2  = findViewById(R.id.add_rgClassTypeMondayQ2)
        rbTheoryMondayQ2  = findViewById(R.id.add_rgTheoryMondayQ2)
        rbPracticeMondayQ2  = findViewById(R.id.add_rgPracticeMondayQ2)

        edtHourBeginTuesday  = findViewById(R.id.add_edtBeginHourTuesday)
        edtHourBeginTuesday = findViewById(R.id.add_edtBeginHourTuesday)
        edtHourEndTuesday = findViewById(R.id.add_edtEndHourTuesday)
        edtClassroomTuesdayQ1  = findViewById(R.id.add_edtClassroomTuesdayQ1)
        rgClassTypeTuesdayQ1  = findViewById(R.id.add_rgClassTypeTuesdayQ1)
        rbTheoryTuesdayQ1  = findViewById(R.id.add_rgTheoryTuesdayQ1)
        rbPracticeTuesdayQ1  = findViewById(R.id.add_rgPracticeTuesdayQ1)
        edtClassroomTuesdayQ2  = findViewById(R.id.add_edtClassroomTuesdayQ2)
        rgClassTypeTuesdayQ2  = findViewById(R.id.add_rgClassTypeTuesdayQ2)
        rbTheoryTuesdayQ2  = findViewById(R.id.add_rgTheoryTuesdayQ2)
        rbPracticeTuesdayQ2  = findViewById(R.id.add_rgPracticeTuesdayQ2)
        edtHourBeginTuesday  = findViewById(R.id.add_edtBeginHourTuesday)

        edtHourBeginWednesday = findViewById(R.id.add_edtBeginHourWednesday)
        edtHourEndWednesday = findViewById(R.id.add_edtEndHourWednesday)
        edtClassroomWednesdayQ1  = findViewById(R.id.add_edtClassroomWednesdayQ1)
        rgClassTypeWednesdayQ1  = findViewById(R.id.add_rgClassTypeWednesdayQ1)
        rbTheoryWednesdayQ1  = findViewById(R.id.add_rgTheoryWednesdayQ1)
        rbPracticeWednesdayQ1  = findViewById(R.id.add_rgPracticeWednesdayQ1)
        edtClassroomWednesdayQ2  = findViewById(R.id.add_edtClassroomWednesdayQ2)
        rgClassTypeWednesdayQ2  = findViewById(R.id.add_rgClassTypeWednesdayQ2)
        rbTheoryWednesdayQ2  = findViewById(R.id.add_rgTheoryWednesdayQ2)
        rbPracticeWednesdayQ2  = findViewById(R.id.add_rgPracticeWednesdayQ2)
        edtHourBeginWednesday  = findViewById(R.id.add_edtBeginHourWednesday)

        edtHourBeginThursday = findViewById(R.id.add_edtBeginHourThursday)
        edtHourEndThursday = findViewById(R.id.add_edtEndHourThursday)
        edtClassroomThursdayQ1  = findViewById(R.id.add_edtClassroomThursdayQ1)
        rgClassTypeThursdayQ1  = findViewById(R.id.add_rgClassTypeThursdayQ1)
        rbTheoryThursdayQ1  = findViewById(R.id.add_rgTheoryThursdayQ1)
        rbPracticeThursdayQ1  = findViewById(R.id.add_rgPracticeThursdayQ1)
        edtClassroomThursdayQ2  = findViewById(R.id.add_edtClassroomThursdayQ2)
        rgClassTypeThursdayQ2  = findViewById(R.id.add_rgClassTypeThursdayQ2)
        rbTheoryThursdayQ2  = findViewById(R.id.add_rgTheoryThursdayQ2)
        rbPracticeThursdayQ2  = findViewById(R.id.add_rgPracticeThursdayQ2)
        edtHourBeginThursday  = findViewById(R.id.add_edtBeginHourThursday)

        edtHourBeginFriday = findViewById(R.id.add_edtBeginHourFriday)
        edtHourEndFriday = findViewById(R.id.add_edtEndHourFriday)
        edtClassroomFridayQ1  = findViewById(R.id.add_edtClassroomFridayQ1)
        rgClassTypeFridayQ1  = findViewById(R.id.add_rgClassTypeFridayQ1)
        rbTheoryFridayQ1  = findViewById(R.id.add_rgTheoryFridayQ1)
        rbPracticeFridayQ1  = findViewById(R.id.add_rgPracticeFridayQ1)
        edtClassroomFridayQ2  = findViewById(R.id.add_edtClassroomFridayQ2)
        rgClassTypeFridayQ2  = findViewById(R.id.add_rgClassTypeFridayQ2)
        rbTheoryFridayQ2  = findViewById(R.id.add_rgTheoryFridayQ2)
        rbPracticeFridayQ2  = findViewById(R.id.add_rgPracticeFridayQ2)
        edtHourBeginFriday  = findViewById(R.id.add_edtBeginHourFriday)

        edtHourBeginFriday = findViewById(R.id.add_edtBeginHourFriday)
        edtHourEndFriday = findViewById(R.id.add_edtEndHourFriday)
        edtClassroomFridayQ1  = findViewById(R.id.add_edtClassroomFridayQ1)
        rgClassTypeFridayQ1  = findViewById(R.id.add_rgClassTypeFridayQ1)
        rbTheoryFridayQ1  = findViewById(R.id.add_rgTheoryFridayQ1)
        rbPracticeFridayQ1  = findViewById(R.id.add_rgPracticeFridayQ1)
        edtClassroomFridayQ2  = findViewById(R.id.add_edtClassroomFridayQ2)
        rgClassTypeFridayQ2  = findViewById(R.id.add_rgClassTypeFridayQ2)
        rbTheoryFridayQ2  = findViewById(R.id.add_rgTheoryFridayQ2)
        rbPracticeFridayQ2  = findViewById(R.id.add_rgPracticeFridayQ2)
        edtHourBeginFriday  = findViewById(R.id.add_edtBeginHourFriday)


        btnSave = findViewById(R.id.add_btnSave)
        llInfoDays = findViewById(R.id.add_llInfoDays)
        clMonday = findViewById(R.id.add_includeMonday)
        clTuesday = findViewById(R.id.add_includeTuesday)
        clWednesday = findViewById(R.id.add_includeWednesday)
        clThursday = findViewById(R.id.add_includeThursday)
        clFriday = findViewById(R.id.add_includeFriday)
        clSaturday = findViewById(R.id.add_includeSaturday)
        checkMonday = findViewById(R.id.add_cbMonday)
        checkTuesday = findViewById(R.id.add_cbTuesday)
        checkWednesday = findViewById(R.id.add_cbWednesday)
        checkThursday = findViewById(R.id.add_cbThursday)
        checkFriday = findViewById(R.id.add_cbFriday)
        checkSaturday = findViewById(R.id.add_cbSaturday)
        llMondayQ1 = findViewById(R.id.add_llMondayQ1)
        llMondayQ2 = findViewById(R.id.add_llMondayQ2)
        llTuesdayQ1 = findViewById(R.id.add_llTuesdayQ1)
        llTuesdayQ2 = findViewById(R.id.add_llTuesdayQ2)
        llWednesdayQ1 = findViewById(R.id.add_llWednesdayQ1)
        llWednesdayQ2 = findViewById(R.id.add_llWednesdayQ2)
        llThursdayQ1 = findViewById(R.id.add_llThursdayQ1)
        llThursdayQ2 = findViewById(R.id.add_llThursdayQ2)
        llFridayQ1 = findViewById(R.id.add_llFridayQ1)
        llFridayQ2 = findViewById(R.id.add_llFridayQ2)
        llSaturdayQ1 = findViewById(R.id.add_llSaturdayQ1)
        llSaturdayQ2 = findViewById(R.id.add_llSaturdayQ2)
        swtMondayQ1 = findViewById(R.id.add_swtMondayQ1)
        swtMondayQ2 = findViewById(R.id.add_swtMondayQ2)
        swtTuesdayQ1 = findViewById(R.id.add_swtTuesdayQ1)
        swtTuesdayQ2 = findViewById(R.id.add_swtTuesdayQ2)
        swtWednesdayQ1 = findViewById(R.id.add_swtWednesdayQ1)
        swtWednesdayQ2 = findViewById(R.id.add_swtWednesdayQ2)
        swtThursdayQ1 = findViewById(R.id.add_swtThursdayQ1)
        swtThursdayQ2 = findViewById(R.id.add_swtThursdayQ2)
        swtFridayQ1 = findViewById(R.id.add_swtFridayQ1)
        swtFridayQ2 = findViewById(R.id.add_swtFridayQ2)
        swtSaturdayQ1 = findViewById(R.id.add_swtSaturdayQ1)
        swtSaturdayQ2 = findViewById(R.id.add_swtSaturdayQ2)
    }
}
