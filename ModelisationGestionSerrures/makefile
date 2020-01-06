.SUFFIXES : # Delete the default suffixes

BASE	= modelisationgestionserrures.tex
SOURCES = $(wildcard *.tex)

UMLETSH=umlet.sh
UMLET = $(wildcard DiagrammesDeCasDUtilisation/*.uxf) \
	$(wildcard DiagrammesDeClasses/*.uxf) \
	$(wildcard DiagrammesDObjets/*.uxf) \
	$(wildcard DiagrammesDeSequence/*.uxf) \
	$(wildcard DiagrammesDeMachineAEtats/*.uxf)
CONVERT_UMLET = $(patsubst %.uxf,%.svg,$(UMLET)) \
	        $(patsubst %.uxf,%.pdf,$(UMLET)) \
	        $(patsubst %.uxf,%.png,$(UMLET))
CLEAN_UMLET   = $(patsubst %.uxf,%.eps,$(UMLET)) \
	        $(patsubst %.uxf,%.pdf,$(UMLET)) \
	        $(patsubst %.uxf,%.png,$(UMLET)) \
	        $(patsubst %.uxf,%.svg,$(UMLET))

all:	$(CONVERT_UMLET) pdf

pdf:	$(SOURCES)
	pdflatex -output-format pdf $(BASE)
	pdflatex -output-format pdf $(BASE)

clean:
	rm -f $(CLEAN_UMLET)
	rm -f *.bak *~
	rm -f *.log *.aux *.bbl *.blg *~ *.bak *.dvi *.out \
	*.toc *.lof *.lot *.brf

%.pdf: %.uxf
	$(UMLETSH) -action=convert -format=pdf -filename=$< -output=$@

%.png: %.uxf
	$(UMLETSH) -action=convert -format=png -filename=$< -output=$@

%.svg: %.uxf
	$(UMLETSH) -action=convert -format=svg -filename=$< -output=$@
