package algocasts

func insertsort(input []int) {
	if input == nil || len(input) == 0 {
		return
	}
	for i := 1; i < len(input); i++ {
		cur := input[i]
		j := i - 1
		for j >= 0 && input[j] > cur {
			input[j+1] = input[j]
			j -= 1
		}
		input[j+1] = cur
	}
}
