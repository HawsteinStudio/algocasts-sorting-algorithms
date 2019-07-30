package algocasts

func selectsortMin(input []int) {
	if input == nil || len(input) == 0 {
		return
	}
	for i := 0; i < len(input); i++ {
		minId := i
		for j := i + 1; j < len(input); j++ {
			if input[j] < input[minId] {
				minId = j
			}
		}
		swap(input, i, minId)
	}
}

func selectsortMax(input []int) {
	if input == nil || len(input) == 0 {
		return
	}
	for i := len(input) - 1; i >= 0; i-- {
		maxId := i
		for j := 0; j < i; j++ {
			if input[j] > input[maxId] {
				maxId = j
			}
		}
		swap(input, i, maxId)
	}
}
