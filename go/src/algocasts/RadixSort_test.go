package algocasts

import (
	"testing"
)

func TestRadixSort8Bit(t *testing.T) {
	tests := []struct {
		name  string
		input []int
		want  []int
	}{
		{"double", []int{2, 1}, []int{1, 2}},
		{"single", []int{1, 5, 4}, []int{1, 4, 5}},
		{"normal", []int{1, 9, 5, 3, 2, 6}, []int{1, 2, 3, 5, 6, 9}},
	}
	for _, tt := range tests {
		RadixSort8Bit(tt.input)
		for i:=0;i<len(tt.input);i++{
			if tt.input[i] != tt.want[i]{
				t.Fatal("meet error")
			}
		}
	}

}

func TestRadixSort4Bit(t *testing.T) {
	tests := []struct {
		name  string
		input []int
		want  []int
	}{
		{"double", []int{2, 1}, []int{1, 2}},
		{"single", []int{1, 5, 4}, []int{1, 4, 5}},
		{"normal", []int{1, 9, 5, 3, 2, 6}, []int{1, 2, 3, 5, 6, 9}},
	}
	for _, tt := range tests {
		RadixSort4Bit(tt.input)
		for i:=0;i<len(tt.input);i++{
			if tt.input[i] != tt.want[i]{
				t.Fatal("meet error")
			}
		}
	}
}