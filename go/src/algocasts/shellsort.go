package algocasts

func shellsort(input []int) {
	if input == nil || len(input) == 0 {
		return
	}
	// >>= means bit counting left move
	for gap := len(input) >> 1; gap > 0; gap >>= 1 {
		for i := gap; i < len(input); i++ {
			cur := input[i]
			j := i - gap
			for j >= 0 && input[j] > cur {
				input[j+gap] = input[j]
				j -= gap
			}
			input[j+gap] = cur
		}
	}
}
