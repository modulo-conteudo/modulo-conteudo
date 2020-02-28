package com.ufabc.moduloconteudo.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.ufabc.moduloconteudo.data.aula.Aula
import com.ufabc.moduloconteudo.data.discente_turma.DiscenteTurma

data class AulasDiscente (
    @Embedded
    val discenteTurma : DiscenteTurma,

    @Relation(parentColumn = "codigo_sie", entityColumn = "codigo_sie")
    val aulasDiscente : List<Aula> = emptyList()
)