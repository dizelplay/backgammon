package com.vorobyov.backgammon.models

import java.util.*

class Point(val pos: Int) {

    private val checkers = Stack<Checker>()

    fun clear() = checkers.clear()

    val checkersCount: Int
    get() = checkers.size


    fun addChecker(checker: Checker) {
        checkers.push(checker)
    }

    fun popChecker(): Checker {
        require(!isFree())

        return checkers.pop()
    }

    fun popFirst(): Checker {
        require(!isFree())

        val first = checkers.first()
        checkers.removeFirst()

        return first
    }

    fun getLastChecker(): Checker {
        require(!isFree())
        return checkers.last()
    }

    fun isFree() = checkers.isEmpty()

    val checkersColor: Checker.Colors
    get() = checkers.last().color

    fun allInHome(whoseHome: Checker.Colors): Boolean {
        when (whoseHome) {
            Checker.Colors.WHITE -> if (pos > 5) return false
            Checker.Colors.BLACK -> if (pos < 23) return false
        }
        
        return true
    }

    /*
    "шашка может двигаться только на открытый пункт,
    то есть на такой, который не занят двумя или более шашками противоположного цвета"
     */
    fun isFreeFor(color: Checker.Colors): Boolean = checkers.filter { it.color != color }.size < 2


    /*
    "Пункт, занятый только одной шашкой,
    носит название «блот».
    Если шашка противоположного цвета останавливается на этом пункте,
    блот считается побитым и кладется на бар"
     */
    fun isBlotFor(color: Checker.Colors): Boolean = checkers.size == 1 && checkers.first().color != color


}

