package algocasts

func CountSortRight(input []int) {
	if len(input) == 0 {
		return
	}
	// k should be a fix size
	k := 10
	counter := make([]int, k+1)
	for _, val := range input {
		counter[val] += 1
	}

	// replace counter array with start position
	start := 0
	for i := 0; i <= k; i++ {
		count := counter[i]
		counter[i] = start
		start += count
	}

	tmp_res := make([]int, len(input))
	for _, val := range input {
		idx := counter[val]
		tmp_res[idx] = val
		counter[val] += 1
	}
	copy(input, tmp_res)

}

func CountSortLeft(input []int) {
	if len(input) == 0 {
		return
	}
	// k should be a fix size
	k := 10
	counter := make([]int, k+1)
	for _, val := range input {
		counter[val] += 1
	}

	// because of 0 pos is n-1
	counter[0] -= 1
	for i := 1; i <= k; i++ {
		counter[i] += counter[i-1]
	}

	tmp_res := make([]int, len(input))
	for i := len(input) - 1; i >= 0; i-- {
		idx := counter[input[i]]
		tmp_res[idx] = input[i]
		counter[input[i]] -= 1
	}
	copy(input, tmp_res)

}
