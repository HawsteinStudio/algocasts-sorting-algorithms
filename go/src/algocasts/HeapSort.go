package algocasts

func shifDown(arr []int, i, end int) {
	parent := i
	child := 2*parent + 1
	for child <= end {
		// get the bigger child (
		if child+1 <= end && arr[child] < arr[child+1] {
			child += 1
		}
		if arr[parent] >= arr[child] {
			break
		}
		swap(arr, parent, child)
		parent = child
		child = 2*parent + 1
	}

}

func buildMapHeap(arr []int, end int) {
	for i := end / 2; i >= 0; i-- {
		shifDown(arr, i, end)
	}
}

func HeapSort(input []int) {
	if len(input) == 0 {
		return
	}
	buildMapHeap(input, len(input)-1)
	for i := len(input) - 1; i > 0; i-- {
		swap(input, 0, i)
		shifDown(input, 0, i-1)
	}
}
