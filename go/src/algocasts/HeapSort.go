package algocasts

import (
	"container/heap"
)

// An IntHeap is a min-heap of ints.
type IntHeap []int

func (h IntHeap) Len() int           { return len(h) }
func (h IntHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h IntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *IntHeap) Push(x interface{}) {
	// Push and Pop use pointer receivers because they modify the slice's length,
	// not just its contents.
	*h = append(*h, x.(int))
}

func (h *IntHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func HeapSortGolangImp(input []int) []int {
	if len(input) == 0 {
		return nil
	}
	minHeap := make(IntHeap, 0, len(input))
	minHeapP := &minHeap
	heap.Init(minHeapP)
	for i := 0; i < len(input); i++ {
		heap.Push(minHeapP, input[i])
		//fmt.Printf("minHeapP is %v", minHeapP)
	}
	result := make([]int, len(input))
	for i := 0; i < len(input); i++ {
		minVal := heap.Pop(minHeapP)
		result[i] = minVal.(int)
	}
	return result
}

// not use container/heap implement

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
