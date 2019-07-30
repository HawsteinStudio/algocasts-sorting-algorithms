package algocasts

func merge(array []int, low int, mid int, high int, tmp []int) {
	var i, j, k int = low, mid + 1, 0
	for i <= mid && j <= high {
		if array[i] > array[j] {
			tmp[k] = array[j]
			k += 1
			j += 1
		} else {
			tmp[k] = array[i]
			k += 1
			i += 1
		}
	}
	for i <= mid {
		tmp[k] = array[i]
		k += 1
		i += 1
	}
	for j <= high {
		tmp[k] = array[j]
		k += 1
		j += 1
	}
	copy(array[low:low+k], tmp[0:k])
}

func mergeSort(array []int, low int, high int, tmp []int) {
	if low < high {
		mid := low + (high-low)/2
		mergeSort(array, low, mid, tmp)
		mergeSort(array, mid+1, high, tmp)
		merge(array, low, mid, high, tmp)
	}
}

func MergeSortRecursive(input []int) {
	if input == nil || len(input) == 0 {
		return
	}
	tmp := make([]int, len(input))
	mergeSort(input, 0, len(input)-1, tmp)
}

func min(a, b int) int {
	if a > b {
		return b
	} else {
		return a
	}
}

func mergerSortIterative(input []int) {
	if input == nil || len(input) == 0 {
		return
	}
	var length int
	tmp := make([]int, len(input))
	for length = 1; length < len(input); length *= 2 {
		for low := 0; low < len(input); low += 2 * length {
			mid := min(low+length-1, len(input)-1)
			high := min(low+2*length-1, len(input)-1)
			merge(input, low, mid, high, tmp)
		}
	}
}
