package pl.pollub.s95408.lab2

class MarksModel constructor(val subject : String, var mark : Int = 2) {
init {
    if (mark < 2 || mark > 5) {
        throw IllegalArgumentException("Mark must be between 2 and 5")
    }
}
}