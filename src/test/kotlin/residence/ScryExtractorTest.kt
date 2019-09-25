package residence

import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.hasSize
import java.io.File
import java.io.InputStream

class ScryExtractorTest {

    val sut = ScryExtractor()
    @Test
    fun emptyInputShouldReturnEmptyList() {
        val result = sut.extractScries ("")
        assertThat(result, hasSize(0))
    }

    @Test
    fun inputWithoutScriesShouldReturnEmptyList() {
        val input = readFileContents("src/test/resources/turnfileWithoutScries.txt")
        val result = sut.extractScries(input)
        assertThat(result, hasSize(0))
    }

    @Test
    fun inputWithOneScryShouldReturnScry() {
        val input = readFileContents("src/test/resources/turnfileWithOneScry.txt")
        val expectedResult = readFileContents("src/test/resources/turnfileWithOneScry_expectedResult.txt")
        val result = sut.extractScries(input)
        assertThat(result, hasSize(1))
        assertThat(result, contains(expectedResult))
    }

    private fun readFileContents(filename: String): String {
        val inputStream: InputStream = File(filename).inputStream()
        return inputStream.bufferedReader().use { it.readText() }
    }
}