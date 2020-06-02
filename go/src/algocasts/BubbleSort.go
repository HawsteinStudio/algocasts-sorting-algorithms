package algocasts

func BubbleSort(input []int) {
	length := len(input)
	for i := length - 1; i > 0; i-- {
		for j := 0; j < i; j++ {
			if input[j] > input[i] {
				tmp := input[j]
				input[j] = input[i]
				input[i] = tmp
			}
		}
	}
}
