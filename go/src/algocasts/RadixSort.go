package algocasts

func sort(input []int, bits int, mask int) {
	if len(input) <= 0 {
		return
	}
	n := len(input)
	count := 32 / bits
	// saving the middle result, clean every time
	tmp := make([]int, n)
	// save bit mask saving part
	indexes := make([]int, 1<<uint(bits))
	for d := 0; d < count; d++ {
		for _, val := range input{
			idx := (val >> uint(bits*d)) & mask
			indexes[idx] += 1
		}
		// Why here needed to mines one
		indexes[0] -= 1

		for i:=1;i<len(indexes);i++{
			indexes[i] = indexes[i] + indexes[i-1]
		}

		for i:=n-1; i>=0;i--{
			idx := (input[i] >> uint(bits * d))
			tmp[indexes[idx]] = input[i]
			indexes[idx] -= 1
		}

		for i:=0;i<len(indexes);i++{
			indexes[i] = 0
		}

		t := input
		input = tmp
		tmp = t
	}
	length := 0
	for ;length<n;length++{
		if input[length] < 0{
			break
		}
	}

	copy(tmp[0:length], input[length:]) // move negative part to tmp
	copy(tmp[length:], input[:length]) // move positive part to tmp
	copy(input[0:], tmp[0:])          // copy tmp to input ,get result

}

func RadixSort8Bit(input []int) {
	sort(input, 8, 0xff)
}

func RadixSort4Bit(input []int) {
	sort(input, 4, 0x0f)
}
