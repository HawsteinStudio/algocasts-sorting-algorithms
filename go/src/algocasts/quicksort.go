package algocasts

func lomutopartition(input []int, low, high int) int {
	pivot := input[high]
	i := low
	for j := low; j <= high; j++ {
		if input[j] < pivot {
			swap(input, i, j)
			i += 1
		}
	}
	swap(input, i, high)
	return i
}

func lomutosort(input []int, low, high int) {
	if low < high {
		k := lomutopartition(input, low, high)
		lomutosort(input, low, k-1)
		lomutosort(input, k+1, high)
	}
}

func Lomutosort(input []int) {
	if input == nil || len(input) == 0 {
		return
	}
	lomutosort(input, 0, len(input)-1)
}

func partition(input []int, low, high int) int {
	mid := low + (high-low)/2
	pivot := input[mid]
	var i, j = low, high
	for true {
		for input[i] < pivot {
			i += 1
		}
		for input[j] > pivot {
			j -= 1
		}
		if i >= j {
			return j
		}
		swap(input, i, j)
	}
	return j
}

func quicksort(input []int, low, high int) {
	if low >= high {
		return
	}
	k := partition(input, low, high)
	quicksort(input, low, k)
	quicksort(input, k+1, high)
}

func Quicksort(input []int) {
	if input == nil || len(input) == 0 {
		return
	}
	quicksort(input, 0, len(input)-1)
}
