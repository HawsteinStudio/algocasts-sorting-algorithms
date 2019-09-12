package algocasts

func CocktailSort(input []int) {
	if len(input) == 0 {
		return
	}
	left := 0
	right := len(input) - 1
	for left < right {
		for i := left; i < right; i++ {
			if input[i] > input[i+1] {
				swap(input, i, i+1)
			}
		}
		right -= 1

		for i := right; i > left; i-- {
			if input[i] < input[i-1] {
				swap(input, i-1, i)
			}
		}
		left += 1
	}
}
