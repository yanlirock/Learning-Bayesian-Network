Q1:

| Dataset   |  Q1 | Q2 | Q3 | Q4 |
| --------- | ----- | ---- | ---- |
| accidents | -65.72876882533004 | -47.88032359587462 | -43.013852479702685 | -47.760117755939646 |
| baudio    | -71.17812237792157 | -64.01945154721751 | -57.89696501610615  | -63.30822559021463 |
| bnetflix  | -93.14242459241842 | -86.92287771451494 | -81.90874704059635 | -86.43400231870228 |
| dna       | -144.8262439010813 | -127.13736581858655 | -122.23491091081937 | -126.57372065952923 |
| jester    | -92.16390258134399 | -84.0076629988646 | -76.72924057494347 | -82.75321433291776 |
| kdd       | -3.5284530006024375 | -3.310831711199247 | -3.2904865125802396 | -3.26588965778046 |
| msnbc     | -9.767254163763425 | -9.435409303894398 | -9.371695548171562 | -9.431685898962115 |
| nltcs     | -13.32128520270276 | -9.751239575329642 | -8.66995719472829 | -9.64984801796504 |
| plants    | -45.10761938833193 | -23.83911455992376 |-18.494230533241172 |-23.495323868802004 |






#### Average Validation set loglikelihood for Mixture Model


| Dataset   |  K=2      | K=5      | K=10     | K=15     | K=20     | K=25     | K=30     | K=35    | K=40   | K =45 | K = 50 |
| -------   | --------  | -------- | -------- | -------  | -------  | -------- |          |         |        | ||
| accidents | -45.7313  | -43.7560 | -42.9740 | -42.5693 | -42.4063 | -42.4724 |          |         |        | ||
| baudio    | -60.3512  | -58.6073 | -57.9775 | -57.7364 | -57.5884 | -57.6312 |          |         |        | ||
| bnetflix  | -85.5821  | -83.4247 | -82.4611 | -82.1289 | -81.9806 | -81.8883 | -81.8481 |-81.7727 |-81.8506 | ||
| dna       | -125.1914 |-122.4935 |-121.6232 |-122.5378 |          |          |          |         |        | ||
| jester    | -80.3007  | -77.8313 | -77.2069 | -77.0836 | -77.0775 | -77.1835 |          |         |        |||
| kdd       | -3.4522   | -3.3822  | -3.5668  |          |          |          |          |         |        |||
| msnbc     | -9.4088   | -9.4333  | -9.4323  | -9.4319  | -9.4322  | -9.4321  |          |         |        |||
| nltcs     | -9.0385   | -8.6825  | -8.6304  | -8.6092  | -8.5927  | -8.5911  | -8.5905  | -8.5902 |-8.5956 |||
| plants    | -22.1335  | -20.5797 | -19.6017 | -19.2020 | -18.9852 | -18.8012 | -18.7313 |-18.6624 |-18.5684|-18.5446 | -18.5503 |


#### Lower Thresholds for Mixture

| Dataset   | K  | average | standard deviation |
| -------   | -- | ----- | ---- |
| accidents | 20 | -43.013852479702685 |0.1060613742496 |
| baudio    | 20 | -57.89696501610615| 0.048066405337541 |
| bnetflix  | 35 |  -81.90874704059635 | 0.044916129513427 |
| dna       | 10 | -122.23491091081937 | 0.84590433387201 |
| jester    | 20 | -76.72924057494347 | 	0.063910584527938 |
| kdd       | 5  | -3.205472683258899 |	0.090707578406819 |
| msnbc     | 2 | -9.371695548171562 | 0.00051037365791307 |
| nltcs     | 30 | -8.66995719472829 | 0.0046464900810703 |
| plants    | 45 | -18.494230533241172 | 	0.038551314867443 |


#### Thresholds for Mixture

| Dataset   | K  | average | standard deviation |
| -------   | -- | ----- | ---- |
| accidents | 20 | -43.013852479702685 |0.1060613742496 |
| baudio    | 20 | -57.89696501610615| 0.048066405337541 |
| bnetflix  | 35 |  -81.90874704059635 | 0.044916129513427 |
| dna       | 10 | -122.23491091081937 | 0.84590433387201 |
| jester    | 20 | -76.72924057494347 | 	0.063910584527938 |
| kdd       | 5  | -3.205472683258899 |	0.090707578406819 |
| msnbc     | 2 | -9.371695548171562 | 0.00051037365791307 |
| nltcs     | 30 | -8.66995719472829 | 0.0046464900810703 |
| plants    | 45 | |

#### Average Validation set loglikelihood for Bagging Model

| Dataset   |  K=2     | K=5      |    K=10  | K=15     | K=20     | K=25     | K=30     | K=35     |
| --------- | -------- | -------- | -------- | -------- | -------- | -------- | -------- | -------- |
| accidents | -47.4221 | -47.3726 | -47.3545 | -47.3646 |
| baudio    | -63.3407 | -63.0600 | -62.9284 | -62.8564 | -62.8653 |
| bnetflix  | -86.7337 | -86.5793 | -86.5181 | -86.4848 | -86.4786 | -86.4692 | -86.4601 | -86.4612 |
| dna       |-126.9819 |-126.5385 |-126.5386 |
| jester    | -83.7051 | -83.2706 | -83.0843 | -83.0184 | -82.9490 | -82.9296 | -82.9335 |
| kdd       | -3.6273  | -3.6078  |  -3.6028 |  -3.5987 |  -3.5994 |
| msnbc     | -9.4335  | -9.4327  |  -9.4321 |  -9.4322 |
| nltcs     | -9.6461  | -9.6194  |  -9.6108 |  -9.6162 |
| plants    | -23.6958 | -23.6043 | -23.5397 | -23.5316 | -23.4963 | -23.5149 |

#### Thresholds for Bagging

| Dataset   | K  | average | standard deviation |
| -------   | -- | ----- | ---- |
| accidents | 10 | -47.760117755939646 | 0.009526357789782 |
| baudio    | 15 | -63.30822559021463| 0.025552648196439 |
| bnetflix  | 30 | -86.43400231870228 | 	0.01280543482531 |
| dna       | 5 | -126.57372065952923 |0.34243628423214 |
| jester    | 25 |  -82.75321433291776 | 0.019927994032406 |
| kdd       | 15 |-3.26588965778046 | 0.004166429855853 |
| msnbc     | 10 | -9.431685898962115 | 0.00089696029712054 |
| nltcs     | 10 | -9.64984801796504 | 0.019723519696117 |
| plants    | 20 | -23.495323868802004 | 0.026811381779713 |
